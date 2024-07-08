package nodos_sd.modelo;

import java.io.*;
import java.net.*;

public class NetworkManager {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public boolean connectToRoom(String pin) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            out.println("JOIN:" + pin);
            String response = in.readLine();
            System.out.println("Respuesta del servidor: " + response);
            return response.equals("OK"); // O alguna otra condición que indique éxito
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public Quiz getQuizForRoom(String pin) {
        // Implementar la lógica para obtener el quiz del servidor
        // Por ahora, retornamos un quiz de ejemplo
        return new Quiz("1", "Quiz de prueba", "Descripción", null, true);
    }

    public void notifyQuestionAnswered(String playerId) {
        // Implementar la lógica para notificar al servidor que se respondió una pregunta
        System.out.println("Notificando al servidor: Pregunta respondida por " + playerId);
    }

    public void notifyGameEnded(String playerId, int score) {
        // Implementar la lógica para notificar al servidor que el juego ha terminado
        System.out.println("Notificando al servidor: Juego terminado para " + playerId + " con puntaje " + score);
    }
}