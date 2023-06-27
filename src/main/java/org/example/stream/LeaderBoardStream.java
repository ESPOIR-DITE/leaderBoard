package org.example.stream;

import model.Players;
import model.Products;
import model.ScoreEvents;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.example.entities.ScoreEventModel;
import org.example.serialization.JsonSerdes;

public class LeaderBoardStream {
    public static void start(){
        StreamsBuilder builder = new StreamsBuilder();

        // register the score events stream
        KStream<byte[], ScoreEvents> scoreEventsKStream = builder.stream(
                "score-events", Consumed.with(Serdes.ByteArray(), JsonSerdes.ScoreEvent()));

        // create the sharded players table
        KTable<String, Players> playersKTable = builder.table(
                "", Consumed.with(Serdes.String(),JsonSerdes.Player()));

        // create the global product table
        GlobalKTable<String, Products> productsGlobalKTable = builder.globalTable(
                "", Consumed.with(Serdes.String(), JsonSerdes.Product()));

        // join params for scoreEvents -> players join
    }
}
