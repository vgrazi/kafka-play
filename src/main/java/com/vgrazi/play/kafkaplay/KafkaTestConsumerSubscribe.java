package com.vgrazi.play.kafkaplay;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaTestConsumerSubscribe {
    // run this main, then from the command line run
    // cd c:\programs\kafka_2.12-2.1.0\
//    bin\windows\kafka-producer-perf-test.bat --topic my-topic --num-records 50 --record-size 1 --throughput 10
//    --producer-props bootstrap.servers=localhost:9092 key.serializer=org.apache.kafka.common.serialization.StringSerializer
//    value.serializer=org.apache.kafka.common.serialization.StringSerializer
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test-consumer-group");

        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<>(props);
        myConsumer.subscribe(Arrays.asList("my-topic", "my-other-topic"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = myConsumer.poll(Duration.ofMillis(10));
                for(ConsumerRecord<String, String> record:records) {
                    System.out.printf("Topic: %s, Partition: %d, Offset:%d, Key: %s, Value:%s%n", record.topic(), record.partition(),
                    record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
