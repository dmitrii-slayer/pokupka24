package org.akatsuki.pokupka24notification.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PurchaseListener {

    private static final Logger log = LoggerFactory.getLogger(PurchaseListener.class);

    @KafkaListener(topics = "${kafka.topic.purchases}")
    public void listen(String data) {
        log.info("Вычитали сообщение: {}", data);
    }

}
