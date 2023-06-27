package org.example.service;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.state.HostInfo;

import java.util.Properties;

import static org.example.stream.LeaderBoardStreamV1.getTopology;

public class StartLeaderBoardService {
    private static final String SCHEMA_REGISTRY_SERVER_URL = "http://localhost:8081";
    public static void start() {
        Topology topology = getTopology();
        Properties config = new Properties();
        config.put("schema.registry.url", SCHEMA_REGISTRY_SERVER_URL);
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_SERVER_CONFIG,"myapp:7000");
        config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        config.put("default.deserialization.exception.handler", LogAndContinueExceptionHandler.class);
        config.put("default.key.serde", Serdes.String().getClass().getName());
        config.put("default.value.serde", SpecificAvroSerde.class);

        System.out.println("Starting Videogame Leaderboard");
        KafkaStreams streams = new KafkaStreams(topology,config);
        streams.setUncaughtExceptionHandler((Thread thread, Throwable throuwable) -> {
            System.out.println(throuwable.toString());
        });
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        streams.start();

        //start service
        HostInfo hostInfo = new HostInfo("myapp",7000);
        LeaderBordService service = new LeaderBordService(hostInfo,streams);
        service.start();
    }

    public static void main(String[] args) {
        start();
    }
}
