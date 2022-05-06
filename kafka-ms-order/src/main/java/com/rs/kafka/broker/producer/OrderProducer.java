package com.rs.kafka.broker.producer;

import com.rs.kafka.broker.message.*;
import org.apache.commons.lang3.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.header.*;
import org.apache.kafka.common.header.internals.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.*;
import org.springframework.stereotype.*;
import org.springframework.util.concurrent.*;

import java.util.*;

/**
 * created by rs 5/6/2022.
 */
@Service
public class OrderProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public void publish(OrderMessage message) {
        var producerRecord = buildProducerRecord(message);

        kafkaTemplate.send(producerRecord)
                .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {

                    @Override
                    public void onSuccess(SendResult<String, OrderMessage> result) {
                        LOG.info("Order {}, item {} published succesfully", message.getOrderNumber(),
                                message.getItemName());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        LOG.warn("Order {}, item {} failed to publish because {}", message.getOrderNumber(),
                                message.getItemName(), ex.getMessage());
                    }
                });

        LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
    }

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
        var surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
        var headers = new ArrayList<Header>();
        var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());

        headers.add(surpriseBonusHeader);

        return new ProducerRecord<String, OrderMessage>("t-commodity-order", null, message.getOrderNumber(), message,
                headers);
    }

}
