package model;

public class ScoreWithPlayer {
    private ScoreEvents scoreEvents;
    private Players players;

    public ScoreWithPlayer(ScoreEvents scoreEvents, Players players) {
        this.scoreEvents = scoreEvents;
        this.players = players;
    }

    public ScoreEvents getScoreEvents() {
        return scoreEvents;
    }

    public void setScoreEvents(ScoreEvents scoreEvents) {
        this.scoreEvents = scoreEvents;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "ScoreWithPlayer{" +
                "scoreEvents=" + scoreEvents +
                ", players=" + players +
                '}';
    }
}
