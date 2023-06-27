package org.example.stream;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import model.ScoreEvents;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class example1 {
    private static final String INPUT_TOPIC = "score-events";
    private static final String OUTPUT_TOPIC = "output_topic";
    private static final String SCHEMA_REGISTRY_URL = "http://localhost:8081";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("application.id", "kafka-streams-example");
        props.put("default.key.serde", Serdes.String().getClass().getName());
        props.put("default.value.serde", SpecificAvroSerde.class);
        props.put("schema.registry.url", SCHEMA_REGISTRY_URL);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, ScoreEvents> inputStream = builder.stream(INPUT_TOPIC);


        inputStream.foreach((key,value) -> {
            System.out.println(value);
        });
        // Configure value serde for Avro messages
//        Serde<ScoreEvents> avroSerde = new SpecificAvroSerde<>();
//        avroSerde.configure(props, false);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }
}
