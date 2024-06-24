package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Empleados implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Empleado_ID;
    private String Empleado_Nombre;
    private String Empleado_Apellido;
    private String Empleado_Email;
    private String Empleado_Fecha_Nacimiento;
    private int Empleado_Sueldo;
    private int Empleado_Cargo_ID;
    private int Empleado_Departamento_ID;

    public Empleados(ObjectInputStream inFromServer2) {
      
    	
    }

    // Getters y setters...

   
    

    public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID del empleado:");
                Empleado_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del empleado:");
                Empleado_Nombre = scanner.nextLine();
                System.out.println("Ingrese el apellido del empleado:");
                Empleado_Apellido = scanner.nextLine();
                System.out.println("Ingrese el email del empleado:");
                Empleado_Email = scanner.nextLine();
                System.out.println("Ingrese la fecha de nacimiento del empleado:");
                Empleado_Fecha_Nacimiento = scanner.nextLine();
                System.out.println("Ingrese el sueldo del empleado:");
                Empleado_Sueldo = scanner.nextInt();
                System.out.println("Ingrese el ID del cargo del empleado:");
                Empleado_Cargo_ID = scanner.nextInt();
                System.out.println("Ingrese ID del departamento del empleado:");
                Empleado_Departamento_ID = scanner.nextInt();
                
            } else if (opcion == 2) {
                System.out.println("Ingrese el nombre del nuevo empleado:");
                Empleado_Nombre = scanner.nextLine();
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID del empleado a eliminar:");
                Empleado_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("empleados");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }

	public int getEmpleado_ID() {
		return Empleado_ID;
	}

	public void setEmpleado_ID(int empleado_ID) {
		Empleado_ID = empleado_ID;
	}

	public String getEmpleado_Nombre() {
		return Empleado_Nombre;
	}

	public void setEmpleado_Nombre(String empleado_Nombre) {
		Empleado_Nombre = empleado_Nombre;
	}

	public String getEmpleado_Apellido() {
		return Empleado_Apellido;
	}

	public void setEmpleado_Apellido(String empleado_Apellido) {
		Empleado_Apellido = empleado_Apellido;
	}

	public String getEmpleado_Email() {
		return Empleado_Email;
	}

	public void setEmpleado_Email(String empleado_Email) {
		Empleado_Email = empleado_Email;
	}

	public String getEmpleado_Fecha_Nacimiento() {
		return Empleado_Fecha_Nacimiento;
	}

	public void setEmpleado_Fecha_Nacimiento(String empleado_Fecha_Nacimiento) {
		Empleado_Fecha_Nacimiento = empleado_Fecha_Nacimiento;
	}

	public int getEmpleado_Sueldo() {
		return Empleado_Sueldo;
	}

	public void setEmpleado_Sueldo(int empleado_Sueldo) {
		Empleado_Sueldo = empleado_Sueldo;
	}

	public int getEmpleado_Cargo_ID() {
		return Empleado_Cargo_ID;
	}

	public void setEmpleado_Cargo_ID(int empleado_Cargo_ID) {
		Empleado_Cargo_ID = empleado_Cargo_ID;
	}

	public int getEmpleado_Departamento_ID() {
		return Empleado_Departamento_ID;
	}

	public void setEmpleado_Departamento_ID(int empleado_Departamento_ID) {
		Empleado_Departamento_ID = empleado_Departamento_ID;
	}
}
