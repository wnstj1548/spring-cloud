package com.example.orderservice.infrastructure.dto;

import lombok.Builder;

@Builder
public record Payload(
        Integer quantity,
        Integer unit_price,
        Integer total_price,
        String user_id,
        Long catalog_id,
        String created_at
) {
}
