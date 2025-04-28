package com.example.orderservice.infrastructure.dto;

public record Field(
        String type,
        boolean optional,
        String field
) {
}
