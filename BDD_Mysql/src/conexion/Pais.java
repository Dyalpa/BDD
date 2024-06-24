package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Pais_ID;
    private String Pais_Nombre;
  

    public Pais(ObjectInputStream inFromServer2) {
          	
    }

    // Getters y setters...

   
    public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID del país:");
                Pais_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre del país:");
                Pais_Nombre = scanner.nextLine();
               
            } else if (opcion == 2) {
                System.out.println("Ingrese el nuevo nombre del país:");
                Pais_Nombre = scanner.nextLine();
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID del país a eliminar:");
                Pais_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("pais");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }

	public int getPais_ID() {
		return Pais_ID;
	}

	public void setPais_ID(int pais_ID) {
		Pais_ID = pais_ID;
	}

	public String getPais_Nombre() {
		return Pais_Nombre;
	}

	public void setPais_Nombre(String pais_Nombre) {
		Pais_Nombre = pais_Nombre;
	}
}