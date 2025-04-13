package com.example.orderservice.application.service;

import com.example.orderservice.presentation.dto.request.CreateOrderRequest;
import com.example.orderservice.presentation.dto.response.ReadOrderResponse;

import java.util.List;

public interface OrderService {
    Long createOrder(CreateOrderRequest request, String userId);
    ReadOrderResponse getOrderById(Long id);
    List<ReadOrderResponse> getOrderByUserId(String userId);
}
