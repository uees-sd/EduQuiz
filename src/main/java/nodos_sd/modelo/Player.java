package nodos_sd.modelo;

import java.util.Map;
import java.util.HashMap;

public class Player {
    private String id;
    private String name;
    private int score;
    private Map<String, String> answers;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.answers = new HashMap<>();
    }

    public void incrementScore(int points) {
        score += points;
    }

    public void submitAnswer(String questionId, String answer) {
        answers.put(questionId, answer);
    }

    public String getAnswer(String questionId) {
        return answers.get(questionId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}