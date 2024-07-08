package nodos_sd.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void showPlayerView() throws Exception {
        loadView("/nodos_sd/vista/player.fxml", "Modo Jugador");
    }

    @FXML
    private void showAdminView() throws Exception {
        loadView("/nodos_sd/vista/admin.fxml", "Modo Administrador");
    }

    private void loadView(String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}