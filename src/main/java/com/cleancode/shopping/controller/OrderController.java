package com.cleancode.shopping.controller;

import com.cleancode.shopping.payload.OrderRequest;
import com.cleancode.shopping.payload.OrderResponse;
import com.cleancode.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final Logger log = LoggerFactory.getLogger("general");
    private final OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        log.info("OrderController | placeOrder is called");

        log.info("OrderController | placeOrder | orderRequest: " + orderRequest.toString());

        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId) {

        log.info("OrderController | getOrderDetails is called");

        OrderResponse orderResponse
                = orderService.getOrderDetails(orderId);

        log.info("OrderController | getOrderDetails | orderResponse : " + orderResponse.toString());

        return new ResponseEntity<>(orderResponse,
                HttpStatus.OK);
    }
}
