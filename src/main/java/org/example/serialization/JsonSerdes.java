package org.example.serialization;

import model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.entities.ScoreEventModel;

public class JsonSerdes {
    public static Serde<Players> Player() {
        JsonDeserializer<Players> playersJsonDeserializer = new JsonDeserializer<>(Players.class);
        JsonSerializer<Players> playersJsonSerializer = new JsonSerializer<>();
        return Serdes.serdeFrom(playersJsonSerializer,playersJsonDeserializer);
    }

    public static Serde<ScoreEvents> ScoreEvent() {
        JsonDeserializer<ScoreEvents> playersJsonDeserializer = new JsonDeserializer<>(ScoreEvents.class);
        JsonSerializer<ScoreEvents> playersJsonSerializer = new JsonSerializer<>();
        return Serdes.serdeFrom(playersJsonSerializer,playersJsonDeserializer);
    }

    public static Serde<Products> Product() {
        JsonDeserializer<Products> playersJsonDeserializer = new JsonDeserializer<>(Products.class);
        JsonSerializer<Products> playersJsonSerializer = new JsonSerializer<>();
        return Serdes.serdeFrom(playersJsonSerializer,playersJsonDeserializer);
    }

    public static Serde<HighScore> HighScore() {
        JsonDeserializer<HighScore> highScoreJsonDeserializer = new JsonDeserializer<>(HighScore.class);
        JsonSerializer<HighScore> highScoreJsonSerializer = new JsonSerializer<>();
        return Serdes.serdeFrom(highScoreJsonSerializer, highScoreJsonDeserializer);
    }

    public static Serde<Enriched> Enriched() {
        JsonDeserializer<Enriched> enrichedJsonDeserializer = new JsonDeserializer<>(Enriched.class);
        JsonSerializer<Enriched> enrichedJsonSerializer = new JsonSerializer<>();
        return Serdes.serdeFrom(enrichedJsonSerializer, enrichedJsonDeserializer);
    }
}
