package org.akatsuki.pokupka24notification.kafka;

import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConsumer {

    private static final Logger log = LoggerFactory.getLogger(PurchaseConsumer.class);

    @KafkaListener(topics = "${kafka.topic.purchases}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "purchaseListener")
    public void handle(@Payload PurchaseDTO purchase) {
        log.info("Вычитали сообщение: {}", purchase);
    }

}
