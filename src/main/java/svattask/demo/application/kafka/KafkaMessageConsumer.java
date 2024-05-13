package svattask.demo.application.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

//    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // Process the received message
    }
}
