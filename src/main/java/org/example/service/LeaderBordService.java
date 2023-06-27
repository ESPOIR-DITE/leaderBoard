package org.example.service;

import io.javalin.Javalin;
import io.javalin.http.Context;
import model.HighScore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyQueryMetadata;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
//import org.apache.kafka.streams.StreamsMetadata;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class LeaderBordService {
    private final HostInfo hostInfo;
    private final KafkaStreams kafkaStreams;

    private static final Logger log = LoggerFactory.getLogger(LeaderBordService.class);


    public LeaderBordService(HostInfo hostInfo, KafkaStreams kafkaStreams) {
        this.hostInfo = hostInfo;
        this.kafkaStreams = kafkaStreams;
    }

    ReadOnlyKeyValueStore<String, HighScore> getStore() {
        return kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(
                        "leader-boards",
                        QueryableStoreTypes.keyValueStore()
                )
        );
    }
    void getAllTest(Context ctx) {
        List<HighScore> myHighScores = new ArrayList<>();
        KeyValueIterator<String, HighScore> highScore = getStore().all();
        while (highScore.hasNext()) {
            myHighScores.add(highScore.next().value);
        }
        ctx.json(myHighScores.toString());
        return;
    }
    void start() {
        Javalin app = Javalin.create().start(hostInfo.port());
        app.get("/leader/boards/{key}", this::getKey);
        app.get("/all/leader/boards", this::getAllKeys);
        app.get("/all/next/boards", this::getAllTest);

    }
    void getKey(Context ctx) {
        String productId = ctx.pathParam("key");
        System.out.println(productId);
        KeyQueryMetadata metadata = kafkaStreams.queryMetadataForKey(
                "leader-boards",
                productId, Serdes.String().serializer()
        );
        System.out.println(metadata);
        if (hostInfo.equals(metadata.activeHost())){
            HighScore highScore = getStore().get(productId);
            if (highScore == null) {
                ctx.status(404);
                return;
            }
            System.out.println(highScore);
            ctx.json(highScore.toString());
            return;

            // a remote instance has the key
//            String remoteHost = metadata.activeHost().host();
//            int remotePort = metadata.activeHost().port();
//            String url =
//                    String.format(
//                            "http://%s:%d/leaderboard/%s",
//                            remoteHost, remotePort, productId);
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            try (Response response = client.newCall(request).execute()) {
//                ctx.result(response.body().string());
//            } catch (Exception e) {
//                ctx.status(500);
//            }
        }
    }

    void getAllKeys(Context ctx) {
        long count = getStore().approximateNumEntries();

        for (StreamsMetadata metadata: kafkaStreams.allMetadataForStore("leader-boards")){
            if (!hostInfo.equals(metadata.hostInfo())){
                continue;
            }
            count += fetchCountFromRemoteInstance(
                    metadata.hostInfo().host(),
                    metadata.hostInfo().port()
            );
        }
        ctx.json(count);
    }
    long fetchCountFromRemoteInstance(String host, int port) {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("http://%s:%d/leader-boards/count/local", host, port);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return Long.parseLong(response.body().string());
        } catch (Exception e) {
            // log error
            log.error("Could not get leaderboard count", e);
            return 0L;
        }
    }
}
