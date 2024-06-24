package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient ObjectInputStream inFromServer;
    private transient Socket socket;

    private int Ciudad_ID;
    private String Ciudad_Nombre;
    private int Pais_Pais_ID;

    public Ciudad(ObjectInputStream inFromServer2) {
      
    	
    }

    // Getters y setters...

    public int getCiudad_ID() {
        return Ciudad_ID;
    }

    public void setCiudad_ID(int ciudad_ID) {
        this.Ciudad_ID = ciudad_ID;
    }

    public String getCiudad_Nombre() {
        return Ciudad_Nombre;
    }

    public void setCiudad_Nombre(String ciudad_Nombre) {
        this.Ciudad_Nombre = ciudad_Nombre;
    }

    public int getPais_Pais_ID() {
        return Pais_Pais_ID;
    }

    public void setPais_Pais_ID(int pais_Pais_ID) {
        this.Pais_Pais_ID = pais_Pais_ID;
    }
    
    

    public String realizarOperacion(ObjectOutputStream outToServer, Scanner scanner, int opcion) {
        try {
            if (opcion == 1) {
                System.out.println("Ingrese el ID de la ciudad:");
                Ciudad_ID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nombre de la ciudad:");
                Ciudad_Nombre = scanner.nextLine();
                System.out.println("Ingrese el ID del país:");
                Pais_Pais_ID = scanner.nextInt();
            } else if (opcion == 2) {
                System.out.println("Ingrese el nuevo nombre de la ciudad:");
                Ciudad_Nombre = scanner.nextLine();
            } else if (opcion == 3) {
                System.out.println("Ingrese el ID de la ciudad a eliminar:");
                Ciudad_ID = scanner.nextInt();
            }

            // Verificar si inFromServer es nulo y crear un nuevo ObjectInputStream
            if (inFromServer == null) {
                inFromServer = new ObjectInputStream(socket.getInputStream());
            }

            outToServer.writeObject("ciudad");
            outToServer.writeObject(this);
            outToServer.writeObject(opcion);

            return (String) inFromServer.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la operación";
        }
    }
}
