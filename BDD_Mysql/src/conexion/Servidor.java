package conexion;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Servidor {
	
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1330);
            System.out.println("Servidor listo para recibir conexiones...");

            // Crear una instancia de Conexion
            final Conexion conexion = new Conexion("jdbc:mysql://localhost:3306/mydb", "root", "Luis_1988");

            while (true) {
                final Socket clientSocket = serverSocket.accept();
               

                // Manejar la conexión del cliente en un hilo separado
                Thread clientThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleClient(clientSocket, conexion);
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, Conexion conexion) {
        try {
            ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            // Utilizar la conexión para operaciones en la base de datos
            Connection connection = conexion.getConnection();

            while (true) {
                // Leer la solicitud del cliente (sentencia SQL)
                String sqlQuery = (String) inFromClient.readObject();
                
               
                
                // Realizar operaciones CRUD en la base de datos según la solicitud
                realizarOperacionSQL(sqlQuery, connection, outToClient);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void realizarOperacionSQL(String sqlQuery, Connection connection, ObjectOutputStream outToClient) {
        try {
            // Ejecutar la sentencia SQL
            if (sqlQuery.toLowerCase().startsWith("select")) {
                // Si es una consulta SELECT, procesar el resultado y enviar datos al cliente
                ResultSet resultSet = executeSelectQuery(sqlQuery, connection);
                enviarDatosSelectAlCliente(resultSet, outToClient);
            } else {
                // Para otras operaciones (INSERT, UPDATE, DELETE), devolver el número de filas afectadas
                int rowsAffected = executeUpdateQuery(sqlQuery, connection);
                outToClient.writeObject("Operación completada. Filas afectadas: " + rowsAffected);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            enviarErrorAlCliente(outToClient, "Error al procesar la operación SQL: " + e.getMessage());
        }
    }

    private static ResultSet executeSelectQuery(String sqlQuery, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }

    private static int executeUpdateQuery(String sqlQuery, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sqlQuery);
    }

    private static void enviarDatosSelectAlCliente(ResultSet resultSet, ObjectOutputStream outToClient) throws SQLException, IOException {
        try {
            // Crear una lista para almacenar los datos de la consulta SELECT
            ArrayList<Object[]> resultData = new ArrayList<>();

            // Iterar sobre el ResultSet y almacenar los datos en la lista
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                resultData.add(rowData);
            }

            // Enviar la lista al cliente
            outToClient.writeObject(resultData);
        } finally {
            // Asegúrate de cerrar el ResultSet en un bloque finally
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void enviarErrorAlCliente(ObjectOutputStream outToClient, String mensajeError) {
        try {
            outToClient.writeObject("Error: " + mensajeError);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
