package com.rs.kafka.broker.consumer;

import com.rs.kafka.broker.message.*;
import org.apache.kafka.clients.consumer.*;
import org.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

/**
 * created by rs 5/9/2022.
 */
@Service
public class OrderConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "t-commodity-order")
    public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var headers = consumerRecord.headers();
        var orderMessage = consumerRecord.value();

        LOG.info("Processing order {},item {}, credit card number {}", orderMessage.getOrderNumber(),
                orderMessage.getItemName(), orderMessage.getCreditCardNumber());
        LOG.info("Headers : ");
        headers.forEach(h -> LOG.info("  key : {}, value : {}", h.key(), new String(h.value())));

        var headerValue = ObjectUtils.isEmpty(headers.lastHeader("surpriseBonus").value()) ? "0"
                : new String(headers.lastHeader("surpriseBonus").value());

        var bonusPercentage = Integer.parseInt(headerValue);
        var bonusAmount = (bonusPercentage / 100d) * orderMessage.getPrice() * orderMessage.getQuantity();

        LOG.info("Bonus amount is {}", bonusAmount);
    }
}
