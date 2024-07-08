package nodos_sd.modelo;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Room {
    private String pin;
    private Quiz quiz;
    private List<Player> players;
    private RoomState state;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public enum RoomState {
        WAITING,
        IN_PROGRESS,
        FINISHED
    }

    public Room(String pin, Quiz quiz) {
        this.pin = pin;
        this.quiz = quiz;
        this.players = new ArrayList<>();
        this.state = RoomState.WAITING;
    }

    public void addPlayer(Player player) {
        if (state == RoomState.WAITING) {
            players.add(player);
        }
    }

    public void startQuiz() {
        if (state == RoomState.WAITING) {
            state = RoomState.IN_PROGRESS;
            startTime = LocalDateTime.now();
        }
    }

    public void endQuiz() {
        if (state == RoomState.IN_PROGRESS) {
            state = RoomState.FINISHED;
            endTime = LocalDateTime.now();
        }
    }

    public List<Player> getLeaderboard() {
        List<Player> leaderboard = new ArrayList<>(players);
        leaderboard.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        return leaderboard;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
   
}