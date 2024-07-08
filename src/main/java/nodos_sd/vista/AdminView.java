package nodos_sd.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {
    private Stage stage;
    private Scene scene;
    private VBox layout;
    private Button createRoomButton;

    public AdminView(Stage stage) {
        this.stage = stage;
        createView();
    }

    private void createView() {
        Label titleLabel = new Label("Panel de Administrador");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        createRoomButton = new Button("Crear Sala");

        layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, createRoomButton);

        scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("EduQuiz - Administrador");
    }

    public Button getCreateRoomButton() {
        return createRoomButton;
    }

    public void show() {
        stage.show();
    }
}