package io.msig.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class KafkaConsumerService {


    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("---- New Kafka Message ----");
        System.out.println("Topic: " + record.topic());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        System.out.println("Timestamp: " + record.timestamp());
        System.out.println("Key: " + record.key());
        System.out.println("Headers: " + readHeaders(record.headers()));
        System.out.println("Value: " + record.value());
    }


    private static Map<String,String> readHeaders(Headers headers) {
        Map<String,String> map = new HashMap<>();
        for (Header header : headers) {
            String key = header.key();
            if (Objects.equals(key, "spring_json_header_types")) {
                continue;
            }
            String value = new String(header.value(), StandardCharsets.UTF_8);
            map.put(key, value);
        }
        return map;
    }
}
