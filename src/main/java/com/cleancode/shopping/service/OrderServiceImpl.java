package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.OrderDetails;
import com.cleancode.shopping.entity.Product;
import com.cleancode.shopping.exception.ProductServiceCustomException;
import com.cleancode.shopping.exception.ShoppingCustomException;
import com.cleancode.shopping.payload.OrderRequest;
import com.cleancode.shopping.payload.OrderResponse;
import com.cleancode.shopping.repository.OrderRepository;
import com.cleancode.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final Logger log = LoggerFactory.getLogger("general");
    private final OrderRepository orderRepository;
    private final String acceptedPaymentMethods = "cash, card, upi";            //Table implementation can be done.
    private final ProductRepository productRepository;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        log.info("OrderServiceImpl | placeOrder is called");
        int randomInt = (int) Math.ceil(Math.random()*5+4);

        if(acceptedPaymentMethods.toLowerCase().contains(orderRequest.getPaymentMode().toLowerCase())){
            OrderDetails orderDetails = OrderDetails.builder()
                    .productId(orderRequest.getProductId())
                    .quantity(orderRequest.getQuantity())
                    .totalAmount(orderRequest.getTotalAmount())
                    .orderStatus("CREATED")
                    .orderDate(Instant.now())
                    .expectedDeliveryDate(Instant.now().plus(randomInt, ChronoUnit.DAYS))
                    .build();

            orderRepository.save(orderDetails);

            log.info("OrderServiceImpl | placeOrder | Order has been placed Successfully.");

            log.info("OrderServiceImpl | placeOrder | Order Places successfully with Order Id: " + orderDetails.getOrderId());

            return "Order Places successfully with Order Id:" + orderDetails.getOrderId();

        }else {
            throw new ShoppingCustomException("Payment method is incorrect","INCORRECT_PAYMENT_METHOD");
        }

    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("OrderServiceImpl | getOrderDetails | Get order details for Order Id : " + orderId);

        OrderDetails order
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new ShoppingCustomException("Order not found for the order Id:" + orderId,
                        "NOT_FOUND"));

        Product product
                = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new ProductServiceCustomException("Product not found for the order Id:" + orderId,
                        "NOT_FOUND"));

        OrderResponse orderResponse
                = OrderResponse
                .builder()
                .orderId(orderId)
                .productName(product.getProductName())
                .quantity(order.getQuantity())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .totalAmount(order.getTotalAmount())
                .expectedDeliveryDate(order.getExpectedDeliveryDate())
                .build();

        return orderResponse;
    }
}
