package nodos_sd;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlUrl = getClass().getResource("/nodos_sd/vista/main.fxml");
        System.out.println("FXML URL: " + fxmlUrl);
        if (fxmlUrl == null) {
            throw new RuntimeException("No se pudo encontrar el archivo FXML");
        }
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("EduQuiz");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}