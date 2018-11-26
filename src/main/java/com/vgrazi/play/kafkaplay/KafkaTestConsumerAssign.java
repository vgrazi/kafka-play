package com.vgrazi.play.kafkaplay;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaTestConsumerAssign {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<>(props);
        myConsumer.assign(Arrays.asList(
                new TopicPartition("my-topic", 0),
                new TopicPartition("my-other-topic", 2)));

        try {
            while (true) {
                ConsumerRecords<String, String> records = myConsumer.poll(Duration.ofMillis(10));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Topic: %s, Partition: %d, Offset:%d, Key: %s, Value:%s%n", record.topic(), record.partition(),
                            record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
