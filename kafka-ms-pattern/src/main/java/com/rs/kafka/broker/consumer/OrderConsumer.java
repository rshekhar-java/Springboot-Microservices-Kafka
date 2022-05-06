package com.rs.kafka.broker.consumer;

import com.rs.kafka.broker.message.*;
import org.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

/**
 * created by rs 5/6/2022.
 */
@Service
public class OrderConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "t-commodity-order")
    public void listen(OrderMessage message) {
        // simulate processing
        var totalItemAmount = message.getPrice() * message.getQuantity();

        LOG.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}",
                message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalItemAmount);
    }
}
