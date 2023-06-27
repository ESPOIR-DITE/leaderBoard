package org.example.entities;

public class ScoreEventModel {
    private double score;
    private long product_id;
    private long player_id;

    public ScoreEventModel() {
    }

    public ScoreEventModel(double score, long product_id, long player_id) {
        this.score = score;
        this.product_id = product_id;
        this.player_id = player_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    @Override
    public String toString() {
        return "ScoreEventModel{" +
                "score=" + score +
                ", product_id=" + product_id +
                ", player_id=" + player_id +
                '}';
    }
}
