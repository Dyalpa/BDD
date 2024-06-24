package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Departamento_ID;
    private String Departamento_Nombre;
    private int Localizaciones_Localizaciones_ID;

    public Departamentos(ObjectInputStream inFromServer2) {
      
    	
    }

    // Getters y setters...

   
    
    

    public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID del departamento:");
                Departamento_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del departamento:");
                Departamento_Nombre = scanner.nextLine();
                System.out.println("Ingrese el ID de localizaciones:");
                Localizaciones_Localizaciones_ID = scanner.nextInt();
                
            } else if (opcion == 2) {
                System.out.println("Ingrese el nuevo nombre del departamento:");
                Departamento_Nombre = scanner.nextLine();
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID del departamento a eliminar:");
                Departamento_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("departamentos");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }

	public int getDepartamento_ID() {
		return Departamento_ID;
	}

	public void setDepartamento_ID(int departamento_ID) {
		Departamento_ID = departamento_ID;
	}

	public String getDepartamento_Nombre() {
		return Departamento_Nombre;
	}

	public void setDepartamento_Nombre(String departamento_Nombre) {
		Departamento_Nombre = departamento_Nombre;
	}

	public int getLocalizaciones_Localizaciones_ID() {
		return Localizaciones_Localizaciones_ID;
	}

	public void setLocalizaciones_Localizaciones_ID(int localizaciones_Localizaciones_ID) {
		Localizaciones_Localizaciones_ID = localizaciones_Localizaciones_ID;
	}
}
