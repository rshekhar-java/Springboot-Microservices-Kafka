package com.rs.kafka.api.server;

import com.rs.kafka.api.request.*;
import com.rs.kafka.api.response.*;
import com.rs.kafka.command.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * created by rs 5/6/2022.
 */
@RestController
@RequestMapping("api/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        // 1. save order using service
        var orderNumber = orderService.saveOrder(request);

        // 2. create response
        var orderResponse = new OrderResponse(orderNumber);

        // 3. return response
        return ResponseEntity.ok().body(orderResponse);
    }
}
