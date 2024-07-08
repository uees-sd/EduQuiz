package nodos_sd.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.util.List;
import java.util.function.Consumer;

import nodos_sd.modelo.NetworkManager;
import nodos_sd.modelo.Quiz;
import nodos_sd.modelo.Question;
import nodos_sd.modelo.Player;

public class GameController {

    @FXML private Label questionLabel;
    @FXML private VBox answersBox;
    @FXML private Button submitButton;
    @FXML private Label timerLabel;
    @FXML private Label scoreLabel;

    private NetworkManager networkManager;
    private Quiz currentQuiz;
    private Player currentPlayer;
    private Question currentQuestion;
    private int currentQuestionIndex;
    private Timeline timer;
    private int timeLeft;
    private Consumer<Void> onQuestionAnsweredHandler;

    public void initialize() {
        submitButton.setOnAction(event -> submitAnswer());
    }

    public void startGame() {
        currentQuestionIndex = 0;
        setNextQuestion();
        updateScore();
    }

    private void setNextQuestion() {
        if (currentQuestionIndex < currentQuiz.getQuestions().size()) {
            currentQuestion = currentQuiz.getQuestions().get(currentQuestionIndex);
            setQuestion(currentQuestion);
            startTimer();
        } else {
            endGame();
        }
    }

    public void setQuestion(Question question) {
        questionLabel.setText(question.getText());
        answersBox.getChildren().clear();

        List<String> options = question.getOptions();
        for (String option : options) {
            RadioButton rb = new RadioButton(option);
            answersBox.getChildren().add(rb);
        }
    }

    private void startTimer() {
        timeLeft = 30; // 30 segundos por pregunta
        updateTimerLabel();

        timer = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                timeLeft--;
                updateTimerLabel();
                if (timeLeft <= 0) {
                    timer.stop();
                    handleTimeUp();
                }
            })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void updateTimerLabel() {
        timerLabel.setText(String.valueOf(timeLeft));
    }

    private void handleTimeUp() {
        submitAnswer();
    }

    @FXML
    private void submitAnswer() {
        timer.stop();

        RadioButton selectedRadioButton = (RadioButton) answersBox.getChildren().stream()
            .filter(node -> node instanceof RadioButton && ((RadioButton) node).isSelected())
            .findFirst()
            .orElse(null);

        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText();
            boolean isCorrect = currentQuestion.isCorrectAnswer(selectedAnswer);
            if (isCorrect) {
                currentPlayer.incrementScore(currentQuestion.getPoints());
            }
        }

        currentQuestionIndex++;
        updateScore();
        setNextQuestion();

        if (onQuestionAnsweredHandler != null) {
            onQuestionAnsweredHandler.accept(null);
        }
    }

    private void updateScore() {
        scoreLabel.setText("Puntaje: " + currentPlayer.getScore());
    }

    private void endGame() {
        timer.stop();
        System.out.println("Juego terminado. Puntaje final: " + currentPlayer.getScore());
        
        // Notificar al servidor que el juego ha terminado
        networkManager.notifyGameEnded(currentPlayer.getId(), currentPlayer.getScore());
        
        // Mostrar pantalla de resultados finales
        showResultsScreen();
    }

    private void showResultsScreen() {
        try {
            Stage resultStage = new Stage();
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            
            grid.add(new Label("Juego terminado"), 0, 0);
            grid.add(new Label("Jugador: " + currentPlayer.getName()), 0, 1);
            grid.add(new Label("Puntaje final: " + currentPlayer.getScore()), 0, 2);
            
            Button closeButton = new Button("Cerrar");
            closeButton.setOnAction(e -> {
                resultStage.close();
                ((Stage) questionLabel.getScene().getWindow()).close(); // Cerrar la ventana del juego
            });
            grid.add(closeButton, 0, 3);
            
            Scene resultScene = new Scene(grid, 300, 200);
            resultStage.setScene(resultScene);
            resultStage.setTitle("Resultados");
            resultStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setQuiz(Quiz quiz) {
        this.currentQuiz = quiz;
    }

    public void setPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setOnQuestionAnswered(Consumer<Void> handler) {
        this.onQuestionAnsweredHandler = handler;
    }
}