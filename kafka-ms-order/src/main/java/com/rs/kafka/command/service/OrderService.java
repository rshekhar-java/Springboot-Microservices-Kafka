package com.rs.kafka.command.service;

import com.rs.kafka.api.request.*;
import com.rs.kafka.command.action.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * created by rs 5/6/2022.
 */
@Service
public class OrderService {
    @Autowired
    private OrderAction orderAction;

    public String saveOrder(OrderRequest request) {
        var order = orderAction.convertToOrder(request);
        orderAction.saveToDatabase(order);

        // flatten message & publish
        order.getItems().forEach(orderAction::publishToKafka);

        return order.getOrderNumber();
    }
}
