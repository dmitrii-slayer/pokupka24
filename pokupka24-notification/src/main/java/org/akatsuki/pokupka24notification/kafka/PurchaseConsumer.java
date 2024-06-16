package org.akatsuki.pokupka24notification.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${kafka.topic.purchases}")
public class PurchaseConsumer {

    private static final Logger log = LoggerFactory.getLogger(PurchaseConsumer.class);

    @KafkaHandler
    public void handle(String data) {
        log.info("Вычитали сообщение: {}", data);
    }

}
