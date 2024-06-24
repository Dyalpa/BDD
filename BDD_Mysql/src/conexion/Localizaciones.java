package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Localizaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Localizaciones_ID;
    private String Localizaciones_Direccion;
    private int Ciudad_Ciudad_ID;
  

    public Localizaciones(ObjectInputStream inFromServer2) {
      
    	
    }

    // Getters y setters...

   
    

    public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID de la localización:");
                Localizaciones_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre de localización:");
                Localizaciones_Direccion = scanner.nextLine();
                System.out.println("Ingrese el ID de la ciudad de la localización:");
                Ciudad_Ciudad_ID = scanner.nextInt();
              
                
            } else if (opcion == 2) {
                System.out.println("Ingrese el nombre de la localización:");
                Localizaciones_Direccion = scanner.nextLine();
                
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID de la ocalización a eliminar:");
                Localizaciones_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("localizaciones");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }

	public int getLocalizaciones_ID() {
		return Localizaciones_ID;
	}

	public void setLocalizaciones_ID(int localizaciones_ID) {
		Localizaciones_ID = localizaciones_ID;
	}

	public String getLocalizaciones_Direccion() {
		return Localizaciones_Direccion;
	}

	public void setLocalizaciones_Direccion(String localizaciones_Direccion) {
		Localizaciones_Direccion = localizaciones_Direccion;
	}

	public int getCiudad_Ciudad_ID() {
		return Ciudad_Ciudad_ID;
	}

	public void setCiudad_Ciudad_ID(int ciudad_Ciudad_ID) {
		Ciudad_Ciudad_ID = ciudad_Ciudad_ID;
	}

	
}
