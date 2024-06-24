package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Cargo_ID;
    private String Cargo_Nombre;
    private int Cargo_Sueldo_Min;
    private int Cargo_Sueldo_Max;

    public Cargo(ObjectInputStream inFromServer2) {
      
    	
    }
    
    

     public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID del cargo:");
                Cargo_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del cargo:");
                Cargo_Nombre = scanner.nextLine();
               
            } else if (opcion == 2) {
                System.out.println("Ingrese el nuevo nombre del cargo:");
                Cargo_Nombre = scanner.nextLine();
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID del cargo a eliminar:");
                Cargo_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("cargo");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }

	public int getCargo_ID() {
		return Cargo_ID;
	}

	public void setCargo_ID(int cargo_ID) {
		Cargo_ID = cargo_ID;
	}

	public String getCargo_Nombre() {
		return Cargo_Nombre;
	}

	public void setCargo_Nombre(String cargo_Nombre) {
		Cargo_Nombre = cargo_Nombre;
	}

	public int getCargo_Sueldo_Min() {
		return Cargo_Sueldo_Min;
	}

	public void setCargo_Sueldo_Min(int cargo_Sueldo_Min) {
		Cargo_Sueldo_Min = cargo_Sueldo_Min;
	}

	public int getCargo_Sueldo_Max() {
		return Cargo_Sueldo_Max;
	}

	public void setCargo_Sueldo_Max(int cargo_Sueldo_Max) {
		Cargo_Sueldo_Max = cargo_Sueldo_Max;
	}
}
