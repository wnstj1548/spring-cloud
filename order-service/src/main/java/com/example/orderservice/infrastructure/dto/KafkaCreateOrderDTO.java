package com.example.orderservice.infrastructure.dto;

public record KafkaCreateOrderDTO(
        Schema schema,
        Payload payload
) {
}
