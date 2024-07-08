package nodos_sd.modelo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RoomManager {
    private Map<String, Room> activeRooms;

    public RoomManager() {
        this.activeRooms = new HashMap<>();
    }

    public String createRoom(Quiz quiz) {
        String pin = generateUniquePin();
        Room room = new Room(pin, quiz);
        activeRooms.put(pin, room);
        return pin;
    }

    public Room getRoom(String pin) {
        return activeRooms.get(pin);
    }

    public void closeRoom(String pin) {
        Room room = activeRooms.get(pin);
        if (room != null) {
            room.endQuiz();
            activeRooms.remove(pin);
        }
    }

    public List<Room> getActiveRooms() {
        return activeRooms.values().stream()
                .filter(room -> room.getState() != Room.RoomState.FINISHED)
                .collect(Collectors.toList());
    }

    private String generateUniquePin() {
        String pin;
        do {
            pin = String.format("%04d", (int)(Math.random() * 10000));
        } while (activeRooms.containsKey(pin));
        return pin;
    }

    // Otros métodos para manejar salas
}