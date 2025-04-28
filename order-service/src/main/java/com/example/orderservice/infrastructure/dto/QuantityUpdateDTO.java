package com.example.orderservice.infrastructure.dto;

import com.example.orderservice.persistence.domain.Order;
import lombok.Builder;

@Builder
public record QuantityUpdateDTO(
        Long catalogId,
        Integer quantity
) {
    public static QuantityUpdateDTO from(Order order) {
        return QuantityUpdateDTO.builder()
                .catalogId(order.getCatalogId())
                .quantity(order.getQuantity())
                .build();
    }
}
