package org.example.topalogy;


import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.example.event.OrderEvent;
import org.example.util.DateUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OrderTopology {

    private static final String INPUT_KAFKA_TOPIC = "order-events";
    private static final String OUTPUT_KAFKA_TOPIC = "event-counts";

    public static Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        // Define key and value serdes
        Serde<String> stringSerde = Serdes.String();
        Serde<OrderEvent> orderEventSerde = new JsonSerde<>(OrderEvent.class);

        // Read from input topic
        KStream<String, OrderEvent> orderEventStream = builder.stream(INPUT_KAFKA_TOPIC, Consumed.with(stringSerde, orderEventSerde));

        // Group by event type and hour, and count events within each window
        KTable<Windowed<String>, Long> eventCounts = orderEventStream
                .groupBy((key, event) -> event.getEventType().toString() + "-" + getHourFromEpoch(event.getActionTime().toInstant().toEpochMilli()))
                .windowedBy(TimeWindows.of(Duration.ofMillis(2000)))
                .count();

        // Write the count of events within each window to the output topic
        eventCounts
                .toStream()
                .map((windowedKey, count) -> {
                    String[] keyParts = windowedKey.key().split("-");
                    String eventType = keyParts[0];
                    int hour = Integer.parseInt(keyParts[1]);
                    long startTimestamp = windowedKey.window().start();
                    String date = DateUtil.convertToDateFormate(startTimestamp);
                    long endTimestamp = windowedKey.window().end();
                    return new KeyValue<>(eventType + "-" + hour, new OutputPayload(eventType, count, date,hour));
                })
                .to(OUTPUT_KAFKA_TOPIC, Produced.with(stringSerde, new JsonSerde<>(OutputPayload.class)));

        return builder.build();
    }

    // Utility method to get the hour from epoch timestamp
    private static int getHourFromEpoch(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return dateTime.getHour();
    }

    // Class to represent the output payload
    static class OutputPayload {
        private final String eventType;
        private final Long count;
        private final Integer hour;
        private final String date;
        public OutputPayload(String eventType, Long count, String date,Integer hour) {
            this.eventType = eventType;
            this.count = count;
            this.hour = hour;
            this.date = date;
        }

        // Getters for serialization
        public String getEventType() {
            return eventType;
        }

        public Long getCount() {
            return count;
        }

        public String getDate() {
            return date;
        }
        public long getHour() {
            return hour;
        }
    }
}
