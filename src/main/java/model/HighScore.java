package model;

import java.util.TreeSet;

public class HighScore {
    private TreeSet<Enriched> highScore = new TreeSet<>();

    public HighScore add(Enriched enriched){
        highScore.add(enriched);
        if(highScore.size() >3 ){
            highScore.remove(highScore.last());
        }
        return this;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "highScore=" + highScore.toString() +
                '}';
    }
}
