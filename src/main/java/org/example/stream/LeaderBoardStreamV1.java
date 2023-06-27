package org.example.stream;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import model.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.example.entities.PlayerModel;
import org.example.entities.ScoreEventModel;
import org.example.serialization.JsonSerdes;

import java.util.Properties;

public class LeaderBoardStreamV1 {
    private static final String SCHEMA_REGISTRY_SERVER_URL = "http://localhost:8081";

    public static Topology getTopology(){
        StreamsBuilder builder = new StreamsBuilder();

        // register the score events stream
        KStream<String, ScoreEvents> scoreEventsKStream = builder.stream(
                "score-events"
                );


        // create the sharded players table
        KTable<String, Players> playersKTable = builder.table(
                "players"
//                ,Consumed.with(Serdes.String(), JsonSerdes.Player())
        );

        // create the global product table
        GlobalKTable<String, Products> productsGlobalKTable = builder.globalTable(
                "products"
//                ,Consumed.with(Serdes.String(), JsonSerdes.Product())
        );

        // Printing Stream and the table
        // there's no print or toStream().print() option for GlobalKTables
        scoreEventsKStream.print(Printed.<String, ScoreEvents>toSysOut().withLabel("score-events topic"));
        playersKTable.toStream().print(Printed.<String, Players>toSysOut().withLabel("players topic"));

//        scoreEventsKStream.foreach((key, value) -> {
//            System.out.println("(DSL) key, " + key);
//            System.out.println("(DSL) Hello, " + value);
//        });

        ValueJoiner<ScoreEvents, Players, ScoreWithPlayer> scoreWithPlayerValueJoiner =
                ((scoreEvents, players) -> new ScoreWithPlayer(scoreEvents,players));

        ValueJoiner<ScoreWithPlayer, Products, Enriched> productsEnrichedValueJoiner =
                (scoreWithPlayer, product) -> new Enriched(scoreWithPlayer,product);

        KeyValueMapper<String, ScoreWithPlayer, String> keyMapper = (left,scoreWithPlayer) -> {
            return String.valueOf(scoreWithPlayer.getScoreEvents().getProductId());
        };

        Joined<String, ScoreEvents, Players> playersJoinedParams = Joined.with(
                Serdes.String(),
                JsonSerdes.ScoreEvent(),
                JsonSerdes.Player()
        );


        KStream<String, ScoreWithPlayer> withPlayer = scoreEventsKStream.join(
                playersKTable,
                scoreWithPlayerValueJoiner,
                playersJoinedParams
        );

        KStream<String, Enriched> withProduct = withPlayer.join(
                productsGlobalKTable,
                keyMapper,
                productsEnrichedValueJoiner
                );

        //Group the enriched stream. This is a prerequisite for aggregating.
        KGroupedStream<String, Enriched> grouped = withProduct.groupBy(
                (key, value) -> value.getProductId().toString(),
                Grouped.with(Serdes.String(), JsonSerdes.Enriched())
        );

        // The initial value of our aggregation will be a new HighScores instance
        Initializer<HighScore> highScoreInitializer = HighScore::new;

        // The logic for aggregating high scores is implemented in the HighScores.add method
        Aggregator<String, Enriched, HighScore> enrichedHighScoreAdd = (key, value, aggregate) -> aggregate.add(value);

        // Materialized state store with minimal configuration
        KTable<String, HighScore> highScoreKTable = grouped.aggregate(
                highScoreInitializer,
                enrichedHighScoreAdd,
                Materialized.<String, HighScore, KeyValueStore<Bytes, byte[]>>
                        as("leader-boards")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(JsonSerdes.HighScore())
        );

        highScoreKTable.toStream().print(Printed.<String, HighScore>toSysOut().withLabel("leader-boards"));

        return builder.build();
    }
    public static void start(){
        Topology topology = getTopology();
        Properties config = new Properties();
        config.put("schema.registry.url", SCHEMA_REGISTRY_SERVER_URL);
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_SERVER_CONFIG,"myapp:8082");
        config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        config.put("default.deserialization.exception.handler", LogAndContinueExceptionHandler.class);
        config.put("default.key.serde", Serdes.String().getClass().getName());
        config.put("default.value.serde", SpecificAvroSerde.class);
//        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, KafkaAvroDeserializer.class);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);

        System.out.println("Starting Videogame Leaderboard");
        KafkaStreams streams = new KafkaStreams(topology,config);
        streams.setUncaughtExceptionHandler((Thread thread, Throwable throuwable) -> {
            System.out.println(throuwable.toString());
        });
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        streams.start();
    }

    public static void main(String[] args) {
        start();
    }
}
