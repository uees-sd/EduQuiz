package nodos_sd.modelo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

public class QuizManager {
    private Map<String, Quiz> quizzes;

    public QuizManager() {
        this.quizzes = new HashMap<>();
    }

    public Quiz createQuiz(String title, String description, Duration timeLimit, boolean isPublic) {
        String id = generateUniqueId();
        Quiz quiz = new Quiz(id, title, description, timeLimit, isPublic);
        quizzes.put(id, quiz);
        return quiz;
    }

    public void deleteQuiz(String id) {
        quizzes.remove(id);
    }

    public Quiz getQuiz(String id) {
        return quizzes.get(id);
    }

    public List<Quiz> getPublicQuizzes() {
        return quizzes.values().stream()
                .filter(Quiz::isPublic)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private String generateUniqueId() {
        // Implementar lógica para generar ID único
        return String.valueOf(System.currentTimeMillis());
    }

    // Otros métodos para manejar quizzes
}