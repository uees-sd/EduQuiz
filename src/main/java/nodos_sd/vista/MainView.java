package nodos_sd.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView {
    private Stage stage;
    private Scene scene;
    private VBox layout;
    private Button playerButton;
    private Button adminButton;

    public MainView(Stage stage) {
        this.stage = stage;
        createView();
    }

    private void createView() {
        Label titleLabel = new Label("EduQuiz");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        playerButton = new Button("Jugador");
        adminButton = new Button("Administrador");

        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, playerButton, adminButton);

        scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("EduQuiz");
    }

    public Button getPlayerButton() {
        return playerButton;
    }

    public Button getAdminButton() {
        return adminButton;
    }

    public void show() {
        stage.show();
    }
}