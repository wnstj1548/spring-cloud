package com.example.orderservice.presentation.controller;

import com.example.orderservice.application.service.OrderService;
import com.example.orderservice.presentation.dto.request.CreateOrderRequest;
import com.example.orderservice.presentation.dto.response.ReadOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request, @PathVariable String userId) {
        orderService.createOrder(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ReadOrderResponse>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ReadOrderResponse> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
