package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Socket socket = new Socket("localhost", 1330);
            System.out.println("Cliente conectado desde " + socket.getLocalAddress() + "\n");

            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            int opcionTabla;

            do {
                System.out.println("Seleccione la tabla que desea culsultar:");
                System.out.println("1. Ciudad");
                System.out.println("2. País");
                System.out.println("3. Cargo");
                System.out.println("4. Departamentos");
                System.out.println("5. Empleados");
                System.out.println("6. Localizaciónes");
                System.out.println("7. Histórico");
                System.out.println("0. Salir");
                opcionTabla = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                if (opcionTabla == 1) {
                    Ciudad ciudad = new Ciudad(inFromServer);
                 // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM ciudad";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA CIUDAD");
                    System.out.println("+----+-------------------+-----------------+");
                    System.out.println("|  ID  | Nombre de la Ciudad | ID del País |");
                    System.out.println("+----+-------------------+-----------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-4s | %-19s | %-11s |\n", rowData[0], rowData[1], rowData[2]);
                    }
                    System.out.println("+----+-------------------+-----------------+");
                    realizarOperacionesCiudad(ciudad, outToServer, scanner, inFromServer);        
                             
                
                    
                } else if (opcionTabla == 2) {
                    Pais pais = new Pais(inFromServer);
                 // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM pais";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA PAIS");
                    System.out.println("+------------------+");
                    System.out.println("| ID | ID del País |");
                    System.out.println("+----+-------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-2s | %-12s|\n", rowData[0], rowData[1]);
                    }
                    System.out.println("+----+-------------+");
                    realizarOperacionesPais(pais, outToServer, scanner);
                    
                } else if (opcionTabla == 3) {
                    Cargo cargo = new Cargo(inFromServer);
                    // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM cargo";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA CARGO");
                    System.out.println("+---------+-----------------+------------------+----------------------+");
                    System.out.println("| Cargo_ID |    Cargo_Nombre    | Cargo_Sueldo_Min | Cargo_Sueldo_Max |");
                    System.out.println("+---------+-----------------+------------------+----------------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-8s | %-18s | %-16s | %-16s |\n", rowData[0], rowData[1], rowData[2], rowData[3]);
                    }
                    System.out.println("+---------+-----------------+------------------+----------------------+");
                    realizarOperacionesCargo(cargo, outToServer, scanner);
                    
                } else if (opcionTabla == 4) {
                    Departamentos departamentos = new Departamentos(inFromServer);
                    // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM departamentos";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA DEPARTAMENTOS");
                    System.out.println("+---------+-----------------+-----------+");
                    System.out.println("| Departamento_ID | Departamento_Nombre |");
                    System.out.println("+---------+-----------------+-----------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-15s | %-19s |\n", rowData[0], rowData[1]);
                    }
                    System.out.println("+---------+-----------------+-----------+");
                    realizarOperacionesDepartamentos(departamentos, outToServer, scanner);
                    
                } else if (opcionTabla == 5) {
                    Empleados empleados = new Empleados(inFromServer);
                    // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM empleados";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA EMPLEADOS");
                    System.out.println("+---------+-----------------+------------------+----------------------------------------------------------------------------------------------------------------------------+------------------+");
                    System.out.println("| Empleado_ID | Empleado_Nombre | Empleado_Apellido |      Empleado_Email      | Empleado_Fecha_Nacimiento | Empleado_Sueldo | Empleado_Cargo_ID | Empleado_Departamento_ID | Empleado_Pais_ID |");
                    System.out.println("+---------+-----------------+------------------+----------------------------------------------------------------------------------------------------------------------------+------------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-11s | %-15s | %-17s | %-24s | %-25s | %-15s | %-17s | %-24s | %-16s |\n", rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], rowData[6], rowData[7], rowData[8]);
                    }
                    System.out.println("+---------+-----------------+------------------+-----------------------------------------------------------------------------------------------------------------------------------------------+");

                    realizarOperacionesEmpleados(empleados, outToServer, scanner);
                    
                } else if (opcionTabla == 6) {
                    Localizaciones localizaciones = new Localizaciones(inFromServer);
                    // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM localizaciones";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA LOCALIZACIONES");
                    System.out.println("+---------+-----------------+------------------+------------------+");
                    System.out.println("| Localizaciones_ID | Localizaciones_Direccion | Ciudad_Ciudad_ID |");
                    System.out.println("+---------+-----------------+------------------+------------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("| %-17s | %-24s | %-16s |\n", rowData[0], rowData[1], rowData[2]);
                    }
                    System.out.println("+---------+-----------------+------------------+------------------+");
                    realizarOperacionesLocalizaciones(localizaciones, outToServer, scanner);
                    
                } else if (opcionTabla == 7) {
                    Historico historico = new Historico(inFromServer);
                    // Realizar una consulta SELECT antes de ejecutar la operación
                    String sqlSelectBefore = "SELECT * FROM historico";
                    outToServer.writeObject(sqlSelectBefore);
                    // Recibir los datos de la tabla desde el servidor
                    ArrayList<Object[]> resultData = (ArrayList<Object[]>) inFromServer.readObject();

                    System.out.println("TABLA HISTORICO");
                    System.out.println("+---------+-----------------+------------------+----------------------+---------------+------------------------+");
                    System.out.println("| Historico_ID |    Empleados_Empleado_ID    | Cargo_Cargo_ID | Historico_Fecha_Inico | Historico_Fecha_Retiro |");
                    System.out.println("+---------+-----------------+------------------+----------------------+---------------+------------------------+");
                    for (Object[] rowData : resultData) {
                        System.out.printf("|     %-8s |          %-18s |       %-8s | %-21s | %-22s |\n", rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]);
                    }
                    System.out.println("+---------+-----------------+------------------+-----------------------+---------------------+------------------+");
                    realizarOperacionesHistorico(historico, outToServer, scanner);
                
                    
                } else if (opcionTabla != 0) {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } while (opcionTabla != 0);

            // Cerrar recursos y finalizar
            socket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    private static void realizarOperacionesCiudad(Ciudad ciudad, ObjectOutputStream outToServer, Scanner scanner, ObjectInputStream inFromServer) throws IOException, ClassNotFoundException {
        System.out.println("Seleccione la operación que desea realizar:");
        System.out.println("1. INSERT");
        System.out.println("2. UPDATE");
        System.out.println("3. DELETE");
        System.out.println("0. Volver al menú principal");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                // INSERT: Capturar datos y enviar al servidor
                System.out.println("Ingrese el ID de la ciudad:");
                int idCiudad = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nombre de la ciudad:");
                String nombreCiudad = scanner.nextLine();
                System.out.println("Ingrese el ID del país:");
                int idPais = scanner.nextInt();

                // Construir la consulta de inserción
                String sqlInsert = "INSERT INTO ciudad (Ciudad_ID, Ciudad_Nombre, Pais_Pais_ID) VALUES ('" + idCiudad + "', '" + nombreCiudad + "', '" + idPais + "')";

                // Enviar la solicitud al servidor
                outToServer.writeObject(sqlInsert);
                break;

            case 2:
                // Solicitar datos para la actualización
                System.out.println("Ingrese el ID de la ciudad a actualizar:");
                int idCiudad_act = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo nombre de la ciudad:");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Ingrese el nuevo ID del país:");
                int nuevoIdPais = scanner.nextInt();

                // Construir la consulta de actualización
                String sqlUpdate = "UPDATE ciudad SET Ciudad_Nombre = '" + nuevoNombre + "', Pais_Pais_ID = '" + nuevoIdPais + "' WHERE Ciudad_ID = " + idCiudad_act;

                // Enviar la solicitud al servidor
                outToServer.writeObject(sqlUpdate);
                break;

            case 3:
                // Solicitar datos para la eliminación
                System.out.println("Ingrese el ID de la ciudad a eliminar:");
                int idCiudad_delt = scanner.nextInt();

                // Construir la consulta de eliminación
                String sqlDelete = "DELETE FROM ciudad WHERE Ciudad_ID = " + idCiudad_delt;

                // Enviar la solicitud al servidor
                outToServer.writeObject(sqlDelete);
                break;

            case 0:
                // Volver al menú principal
                return;

            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                return;
        }
       
    }

	private static void realizarOperacionesPais(Pais pais, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID del país:");
	                int idPais = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nombre del país:");
	                String nombrePais = scanner.nextLine();
	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO pais (Pais_ID, Pais_Nombre) VALUES ('" + idPais + "', '" + nombrePais +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            case 2:
	                // Solicitar datos para la actualización
	                System.out.println("Ingrese el ID del país a actualizar:");
	                int idPais_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo nombre del país:");
	                String nuevoNombre = scanner.nextLine();

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE Pais SET Pais_Nombre = '" + nuevoNombre + "' WHERE Pais_ID = " + idPais_act;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID del país a eliminar:");
	                int idPais_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM pais WHERE Pais_ID = " + idPais_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
	private static void realizarOperacionesCargo(Cargo cargo, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID del cargo:");
	                int idCargo = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nombre del cargo:");
	                String nombreCargo = scanner.nextLine();
	                System.out.println("Ingrese el sueldo mínimo del cargo:");
	                int sueldominCargo = scanner.nextInt();
	                System.out.println("Ingrese el sueldo máximo del cargo:");
	                int sueldomaxCargo = scanner.nextInt();
	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO cargo (Cargo_ID, Cargo_Nombre, Cargo_Sueldo_Min, Cargo_Sueldo_Max) VALUES ('" + idCargo + "', '" + nombreCargo +"', '" + sueldominCargo +"', '" + sueldomaxCargo +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            case 2:
	                // Solicitar datos para la actualización
	                System.out.println("Ingrese el ID del cargo a actualizar:");
	                int idCargo_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo nombre del cargo:");
	                String nuevoNombre = scanner.nextLine();
	                System.out.println("Ingrese el nuevo sueldo mínimo del cargo:");
	                int nuevosueldomin = scanner.nextInt();
	                System.out.println("Ingrese el nuevo sueldo máximo del cargo:");
	            int nuevosueldomax = scanner.nextInt();

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE cargo SET Cargo_Nombre = '" + nuevoNombre + "', Cargo_Sueldo_Min = " + nuevosueldomin + ", Cargo_Sueldo_Max = " + nuevosueldomax + " WHERE Cargo_ID = " + idCargo_act;
	                
	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID del cargo a eliminar:");
	                int idCargo_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM cargo WHERE Cargo_ID = " + idCargo_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
	private static void realizarOperacionesDepartamentos(Departamentos departamentos, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID del departamento:");
	                int idDepartamento = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nombre del departamento:");
	                String nombreDepartamento = scanner.nextLine();
	             
	                	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO departamentos (Departamento_ID, Departamento_Nombre) VALUES ('" + idDepartamento + "', '" + nombreDepartamento +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            case 2:
	                // Solicitar datos para la actualización
	                System.out.println("Ingrese el ID del departamento a actualizar:");
	                int idDepartamento_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo nombre del departamento:");
	                String nuevoNombre = scanner.nextLine();
	               	              

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE departamentos SET Departamento_Nombre = '" + nuevoNombre + " WHERE Departamento_ID = " + idDepartamento_act;
	                		
	                		// Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID del departamento a eliminar:");
	                int idDepartamento_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM departamentos WHERE Departamento_ID = " + idDepartamento_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
	private static void realizarOperacionesEmpleados(Empleados empleados, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID del empleado:");
	                int idEmpleado = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nombre del empleado:");
	                String nombreEmpleado = scanner.nextLine();
	                System.out.println("Ingrese el apellido del empleado:");
	                String apellidoEmpleado = scanner.nextLine();
	                System.out.println("Ingrese el email del empleado:");
	                String emailEmpleado = scanner.nextLine();
	                System.out.println("Ingrese la fecha de nacimiento del empleado:");
	                String fechanacimientoEmpleado = scanner.nextLine();
	                System.out.println("Ingrese el sueldo del empleado:");
	                int sueldoEmpleado = scanner.nextInt();
	                System.out.println("Ingrese el ID del caro del empeado:");
	                int idcargoEmpleado = scanner.nextInt();
	                System.out.println("Ingrese el ID del departamento del empleado:");
	                int idDepartamentoEmpleado = scanner.nextInt();
	                System.out.println("Ingrese el ID del país del empleado:");
	                int idPaisEmpleado = scanner.nextInt();
	                	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO empleados (Empleado_ID, Empleado_Nombre, Empleado_Apellido, Empleado_Email, Empleado_Fecha_Nacimiento, Empleado_Sueldo, Empleado_Cargo_ID, Empleado_Departamento_ID, Empleado_Pais_ID) VALUES ('" + idEmpleado + "', '" + nombreEmpleado +"', '" + apellidoEmpleado +"', '" + emailEmpleado +"', '" + fechanacimientoEmpleado +"', '" + sueldoEmpleado +"', '" + idcargoEmpleado +"', '" + idDepartamentoEmpleado +"', '" + idPaisEmpleado +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            case 2:
	                // Solicitar datos para la actualización
	                System.out.println("Ingrese el ID del empleado a actualizar:");
	                int idEmpleado_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo nombre del empleado:");
	                String nuevoNombre = scanner.nextLine();
	                System.out.println("Ingrese el nuevo apellido del empleado:");
	                String nuevoApellido = scanner.nextLine();
	                System.out.println("Ingrese el nuevo email del empleado:");
	                String nuevoEmail = scanner.nextLine();
	                System.out.println("Ingrese la nueva fecha de nacimiento del empleado:");
	                String nuevoFechanacimiento = scanner.nextLine();
	                System.out.println("Ingrese el nuevo sueldo del empleado:");
	                int nuevoSueldo = scanner.nextInt();
	                System.out.println("Ingrese el nuevo ID de cargo del empleado:");
	                int nuevoIdcargo = scanner.nextInt();
	                System.out.println("Ingrese el nuevo ID de departamento del empleado:");
	                int nuevoIdDepartamento = scanner.nextInt();
	                System.out.println("Ingrese el nuevo ID del país del empleado:");
	                int nuevoIdPais = scanner.nextInt();

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE empleados SET Empleado_Nombre = '" + nuevoNombre + "', Empleado_Apellido = '" + nuevoApellido + "', Empleado_Email = '" + nuevoEmail + "', Empleado_Fecha_Nacimiento = '" + nuevoFechanacimiento + "', Empleado_Sueldo = " + nuevoSueldo + ", Empleado_Cargo_ID = " + nuevoIdcargo + ", Empleado_Departamento_ID = " + nuevoIdDepartamento + ", Empleado_Pais_ID = " + nuevoIdPais + " WHERE Empleado_ID = " + idEmpleado_act;
	                
	                		// Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID del empleado a eliminar:");
	                int idEmpleado_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM empleados WHERE Empleado_ID = " + idEmpleado_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
	private static void realizarOperacionesLocalizaciones(Localizaciones localizaciones, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID de la localización:");
	                int idLocalizacion = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nombre de la localizacion:");
	                String nombreLocalizacion = scanner.nextLine();
	                System.out.println("Ingrese el ID de la ciudad de la localización:");
	                int IdCiudadLocalizacion = scanner.nextInt();
                	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO localizaciones (Localizaciones_ID, Localizaciones_Direccion, Ciudad_Ciudad_ID) VALUES ('" + idLocalizacion + "', '" + nombreLocalizacion +"', '" + IdCiudadLocalizacion +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            
	            case 2:
	                // Solicitar datos para la actualización
	                System.out.println("Ingrese el ID de la localización a actualizar:");
	                int idLocalizacion_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo nombre de la localización:");
	                String nuevoNombre = scanner.nextLine();
	                System.out.println("Ingrese el nuevo ID de la ciudad de la localización:");
	                int nuevoIdCiudadLocalizacion = scanner.nextInt();

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE localizaciones SET Localizaciones_Direccion = '" + nuevoNombre + "', Ciudad_Ciudad_ID = " + nuevoIdCiudadLocalizacion + " WHERE Localizaciones_ID = " + idLocalizacion_act;
	                
	                		// Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID de la localización a eliminar:");
	                int idLocalizacion_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM localizaciones WHERE Localizaciones_ID = " + idLocalizacion_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
	private static void realizarOperacionesHistorico(Historico historico, ObjectOutputStream outToServer, Scanner scanner) throws IOException, ClassNotFoundException {
		
		 System.out.println("Seleccione la operación que desea realizar:");
	        System.out.println("1. INSERT");
	        System.out.println("2. UPDATE");
	        System.out.println("3. DELETE");
	        System.out.println("0. Volver al menú principal");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea

	        switch (opcion) {
	            case 1:
	                // INSERT: Capturar datos y enviar al servidor
	                System.out.println("Ingrese el ID del historico:");
	                int idHistorico = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el ID del empleado:");
	                int IdEmpleado = scanner.nextInt();
	                System.out.println("Ingrese el ID del cargo:");
	                int IdCargo = scanner.nextInt();
	                scanner.nextLine();
	                System.out.println("Ingrese la fecha de inicio:");
	                String FechainicioHistorico = scanner.nextLine();
	                System.out.println("Ingrese la fecha de retiro:");
	                String FecharetiroHistorico = scanner.nextLine();
               	                

	                // Construir la consulta de inserción
	                String sqlInsert = "INSERT INTO historico (Historico_ID, Empleados_Empleado_ID, Cargo_Cargo_ID, Historico_Fecha_Inico, Historico_Fecha_Retiro) VALUES ('" + idHistorico + "', '" + IdEmpleado +"', '" + IdCargo +"', '" + FechainicioHistorico +"', '" + FecharetiroHistorico +"')";

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlInsert);
	                break;

	            
	            case 2:
	                // Solicitar datos para la actualización
	            	System.out.println("Ingrese el ID del historico a actualizar:");
	                int idHistorico_act = scanner.nextInt();
	                scanner.nextLine(); // Consumir el salto de línea
	                System.out.println("Ingrese el nuevo ID del empleado:");
	                int nuevoIdEmpleado = scanner.nextInt();
	                System.out.println("Ingrese el nuevo ID del cargo:");
	                int nuevoIdCargo = scanner.nextInt();
	                scanner.nextLine();
	                System.out.println("Ingrese la nueva fecha de inicio:");
	                String nuevaFechainicioHistorico = scanner.nextLine();
	                System.out.println("Ingrese la nueva fecha de retiro:");
	                String nuevaFecharetiroHistorico = scanner.nextLine();

	                // Construir la consulta de actualización
	                String sqlUpdate = "UPDATE historico SET Empleados_Empleado_ID = '" + nuevoIdEmpleado + "', Cargo_Cargo_ID = '" + nuevoIdCargo + "', Historico_Fecha_Inico = '" + nuevaFechainicioHistorico + "', Historico_Fecha_Retiro = '" + nuevaFecharetiroHistorico + "' WHERE Historico_ID = " + idHistorico_act;
	                
	                		// Enviar la solicitud al servidor
	                outToServer.writeObject(sqlUpdate);
	                break;
	                

	            case 3:
	                // Solicitar datos para la eliminación
	                System.out.println("Ingrese el ID del histórico a eliminar:");
	                int idHistorico_delt = scanner.nextInt();

	                // Construir la consulta de eliminación
	                String sqlDelete = "DELETE FROM historico WHERE Historico_ID = " + idHistorico_delt;

	                // Enviar la solicitud al servidor
	                outToServer.writeObject(sqlDelete);
	                break;

	            case 0:
	                // Volver al menú principal
	                return;

	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                return;
	        }
	       
	    }
	
    }
