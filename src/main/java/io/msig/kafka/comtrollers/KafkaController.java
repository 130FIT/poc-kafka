package io.msig.kafka.comtrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.msig.kafka.services.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producer;

    public KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) throws JsonProcessingException {
        producer.sendMessage("my-topic", message);
        return "Message sent to Kafka!";
    }
}

