package nodos_sd.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nodos_sd.modelo.NetworkManager;
import nodos_sd.modelo.QuizManager;
import nodos_sd.modelo.Quiz;
import nodos_sd.modelo.Player;

public class PlayerController {

    @FXML private TextField pinTextField;
    private NetworkManager networkManager;
    private QuizManager quizManager;
    private String playerId;
    private String playerName;

    public PlayerController() {
        networkManager = new NetworkManager();
        quizManager = new QuizManager();
        playerId = generatePlayerId();
        playerName = "Jugador"; // Podrías pedir al usuario que ingrese su nombre
    }

    @FXML
    private void joinRoom() {
        String pin = pinTextField.getText();
        if (pin != null && !pin.isEmpty()) {
            boolean joined = networkManager.connectToRoom(pin);
            if (joined) {
                System.out.println("Unido exitosamente a la sala");
                openGameView(pin);
            } else {
                System.out.println("No se pudo unir a la sala");
                // Aquí podrías mostrar un mensaje de error al usuario
            }
        } else {
            System.out.println("Por favor, ingrese un PIN válido");
            // Aquí podrías mostrar un mensaje de error al usuario
        }
    }

    private void openGameView(String pin) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/nodos_sd/vista/game.fxml"));
            Parent root = loader.load();
            
            GameController gameController = loader.getController();
            
            Quiz currentQuiz = networkManager.getQuizForRoom(pin);
            Player player = new Player(playerId, playerName);

            gameController.setQuiz(currentQuiz);
            gameController.setPlayer(player);
            gameController.setNetworkManager(networkManager);

            gameController.setOnQuestionAnswered(unused -> handleQuestionAnswered());

            gameController.startGame();

            Stage stage = new Stage();
            stage.setTitle("EduQuiz - Juego");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

            ((Stage) pinTextField.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleQuestionAnswered() {
        System.out.println("Pregunta respondida");
        networkManager.notifyQuestionAnswered(playerId);
    }

    private String generatePlayerId() {
        return "player_" + System.currentTimeMillis();
    }
}