package com.vgrazi.play.kafkaplay;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaTestProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> myProducer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 150; i++) {
                myProducer.send(new ProducerRecord<>("my-topic", String.valueOf(i), "MyMessage: " + String.valueOf(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
