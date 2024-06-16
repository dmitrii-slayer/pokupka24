package org.akatsuki.pokupka24notification.kafka;

import org.akatsuki.pokupka24.dto.ProductDTO;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.akatsuki.pokupka24notification.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PurchaseConsumer {

    private static final Logger log = LoggerFactory.getLogger(PurchaseConsumer.class);

    @Autowired
    private UserClient userClient;

    @KafkaListener(topics = "${kafka.topic.purchases}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "purchaseListener")
    public void handle(@Payload PurchaseDTO purchase) {
        log.info("Вычитали сообщение: {}", purchase);

        log.info("Обращаемся к внешнему сервису...");
        UserDTO user = userClient.findUserById(purchase.getUserAccount().getUserId()).getBody();
        if (user != null) {
            log.info("Email пользователя: {}", user.getEmail());
            // отправка email пользователю
            Set<String> productsTitles = purchase.getProducts().stream()
                    .map(ProductDTO::getTitle).collect(Collectors.toSet());
            System.out.println("Поздравляем с покупкой: " + productsTitles);
        } else {
            log.warn("По покупке {} не удалось найти информацию о покупателе", purchase.getPurchaseId());
        }
    }

}
