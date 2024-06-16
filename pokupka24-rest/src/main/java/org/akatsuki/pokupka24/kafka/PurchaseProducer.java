package org.akatsuki.pokupka24.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.akatsuki.pokupka24.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PurchaseMapper purchaseMapper;

    @Value("${kafka.topic.purchases}")
    private String topicName;

    public void sendMessage(Purchase purchase) {
        PurchaseDTO purchaseDTO = purchaseMapper.toDTO(purchase);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, purchaseDTO);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[" + purchaseDTO +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error("Unable to send message=[" +
                        purchaseDTO + "] due to : " + ex.getMessage());
            }
        });
    }
}
