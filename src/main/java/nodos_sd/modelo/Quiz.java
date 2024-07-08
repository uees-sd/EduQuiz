package nodos_sd.modelo;

import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

public class Quiz {
    private String id;
    private String title;
    private String description;
    private List<Question> questions;
    private Duration timeLimit;
    private boolean isPublic;

    public Quiz(String id, String title, String description, Duration timeLimit, boolean isPublic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
        this.timeLimit = timeLimit;
        this.isPublic = isPublic;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public int getTotalPoints() {
        return questions.stream().mapToInt(Question::getPoints).sum();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Duration timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}