package nodos_sd.controlador;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 5000;
    private static Map<String, Set<Socket>> rooms = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.startsWith("CREATE")) {
                        String roomPin = createRoom();
                        out.println(roomPin);
                    } else if (inputLine.startsWith("JOIN:")) {
                        String pin = inputLine.split(":")[1];
                        joinRoom(pin);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String createRoom() {
            String roomPin = generateRoomPin();
            rooms.put(roomPin, new HashSet<>());
            rooms.get(roomPin).add(socket);
            return roomPin;
        }

        private void joinRoom(String pin) {
            if (rooms.containsKey(pin)) {
                rooms.get(pin).add(socket);
                out.println("Unido a la sala " + pin);
            } else {
                out.println("Sala no encontrada");
            }
        }

        private String generateRoomPin() {
            return String.format("%04d", new Random().nextInt(10000));
        }
    }
}