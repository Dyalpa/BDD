package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection connection;

    public Conexion(String jdbcUrl, String usuario, String contraseña) {
        try {
            // Cargar el controlador JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            this.connection = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
            System.out.println("Conexión a la base de datos establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}
