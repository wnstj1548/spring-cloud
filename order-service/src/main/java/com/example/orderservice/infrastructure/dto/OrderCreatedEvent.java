package com.example.orderservice.infrastructure.dto;

import com.example.orderservice.persistence.domain.Order;
import lombok.Builder;

@Builder
public record OrderCreatedEvent(
        Long catalogId,
        Integer quantity
) {
    public static OrderCreatedEvent from(Order order) {
        return OrderCreatedEvent.builder()
                .catalogId(order.getCatalogId())
                .quantity(order.getQuantity())
                .build();
    }
}
