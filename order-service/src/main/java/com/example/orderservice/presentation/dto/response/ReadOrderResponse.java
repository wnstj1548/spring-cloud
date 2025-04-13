package com.example.orderservice.presentation.dto.response;

import com.example.orderservice.persistence.domain.Order;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReadOrderResponse(
        Long orderId,
        Integer quantity,
        Integer unitPrice,
        Integer totalPrice,
        Long catalogId,
        LocalDateTime createdAt
) {
    public static ReadOrderResponse fromEntity(Order order) {
        return ReadOrderResponse.builder()
                .orderId(order.getId())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .catalogId(order.getCatalogId())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
