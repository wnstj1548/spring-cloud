package com.example.orderservice.presentation.dto.request;

import com.example.orderservice.persistence.domain.Order;

public record CreateOrderRequest(
        Integer quantity,
        Integer unitPrice,
        Long catalogId
) {
    public Order toEntity(String userId) {
        return Order.builder()
                .quantity(quantity)
                .unitPrice(unitPrice)
                .userId(userId)
                .catalogId(catalogId)
                .totalPrice(unitPrice * quantity)
                .build();
    }
}
