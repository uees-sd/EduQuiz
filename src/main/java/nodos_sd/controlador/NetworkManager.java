package nodos_sd.controlador;

import java.io.*;
import java.net.*;

public class NetworkManager {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public void connectToRoom(String pin) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            out.println("JOIN:" + pin);
            String response = in.readLine();
            System.out.println("Respuesta del servidor: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createRoom() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            out.println("CREATE");
            String roomPin = in.readLine();
            return roomPin;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}