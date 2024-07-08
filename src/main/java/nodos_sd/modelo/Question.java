package nodos_sd.modelo;

import java.util.List;
import java.util.ArrayList;

public class Question {
    private String id;
    private String text;
    private List<String> options;
    private int correctOptionIndex;
    private int points;
    private QuestionType type;

    public enum QuestionType {
        MULTIPLE_CHOICE,
        TRUE_FALSE,
        SHORT_ANSWER
    }

    public Question(String id, String text, int points, QuestionType type) {
        this.id = id;
        this.text = text;
        this.options = new ArrayList<>();
        this.points = points;
        this.type = type;
    }

    public void addOption(String option) {
        options.add(option);
    }

    public boolean isCorrectAnswer(String answer) {
        if (type == QuestionType.MULTIPLE_CHOICE || type == QuestionType.TRUE_FALSE) {
            return Integer.parseInt(answer) == correctOptionIndex;
        } else {
            // Para preguntas de respuesta corta, podría implementarse una lógica más compleja
            return answer.equalsIgnoreCase(options.get(correctOptionIndex));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

}