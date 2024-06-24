package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Historico implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Historico_ID;
    private int Empleados_Empleado_ID;
    private int Cargo_Cargo_ID;
    private String Historico_Fecha_Inico;
    private String Historico_Fecha_Retiro;

    public Historico(ObjectInputStream inFromServer2) {
      
    	
    }
    
    

     public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID del histórico:");
                Historico_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el ID del empleado:");
                Empleados_Empleado_ID = scanner.nextInt();
                System.out.println("Ingrese el ID del cargo:");
                Cargo_Cargo_ID = scanner.nextInt();
                System.out.println("Ingrese la fecha de inicio:");
                Historico_Fecha_Inico = scanner.nextLine();
                System.out.println("Ingrese la fecha de retiro:");
                Historico_Fecha_Retiro = scanner.nextLine();
               
            } else if (opcion == 2) {
                System.out.println("Ingrese el nuevo ID del empleado:");
                Empleados_Empleado_ID = scanner.nextInt();
                System.out.println("Ingrese el nuevo ID del cargo:");
                Cargo_Cargo_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la nueva fecha de inicio:");
                Historico_Fecha_Inico = scanner.nextLine();
                System.out.println("Ingrese la nueva fecha de retiro:");
                Historico_Fecha_Retiro = scanner.nextLine();
                
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID del historico a eliminar:");
                Historico_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("historico");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }



	public int getHistorico_ID() {
		return Historico_ID;
	}



	public void setHistorico_ID(int historico_ID) {
		Historico_ID = historico_ID;
	}



	public int getEmpleados_Empleado_ID() {
		return Empleados_Empleado_ID;
	}



	public void setEmpleados_Empleado_ID(int empleados_Empleado_ID) {
		Empleados_Empleado_ID = empleados_Empleado_ID;
	}



	public int getCargo_Cargo_ID() {
		return Cargo_Cargo_ID;
	}



	public void setCargo_Cargo_ID(int cargo_Cargo_ID) {
		Cargo_Cargo_ID = cargo_Cargo_ID;
	}



	public String getHistorico_Fecha_Inico() {
		return Historico_Fecha_Inico;
	}



	public void setHistorico_Fecha_Inico(String historico_Fecha_Inico) {
		Historico_Fecha_Inico = historico_Fecha_Inico;
	}



	public String getHistorico_Fecha_Retiro() {
		return Historico_Fecha_Retiro;
	}



	public void setHistorico_Fecha_Retiro(String historico_Fecha_Retiro) {
		Historico_Fecha_Retiro = historico_Fecha_Retiro;
	}



	

	
}
