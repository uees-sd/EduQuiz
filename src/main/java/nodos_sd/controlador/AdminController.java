package nodos_sd.controlador;

import nodos_sd.modelo.NetworkManager;
import javafx.fxml.FXML;

public class AdminController {

    private NetworkManager networkManager;

    public AdminController() {
        networkManager = new NetworkManager();
    }

    @FXML
    private void createRoom() {
        String roomPin = networkManager.createRoom();
        // Mostrar el PIN de la sala creada al administrador
        System.out.println("Sala creada con PIN: " + roomPin);
    }
}