package io.msig.kafka.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.msig.kafka.dto.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.kafkaTemplate.send(MessageBuilder.withPayload(mapper.writeValueAsString(new MessageDTO(message)))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, "1")
                .setHeader("correlationId", UUID.randomUUID().toString())
                .setHeader("messageType", "MyEvent")
                .build()
        );
    }

}

