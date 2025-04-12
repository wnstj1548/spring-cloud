package com.example.catalogservice.presentation.dto.response;

import com.example.catalogservice.persistence.domain.Catalog;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ReadCatalogResponse(
        Long id,
        String productName,
        Integer unitPrice,
        Integer stock,
        LocalDateTime createdAt
) {
    public static ReadCatalogResponse fromEntity(Catalog catalog) {
        return ReadCatalogResponse.builder()
                .id(catalog.getId())
                .productName(catalog.getProductName())
                .unitPrice(catalog.getUnitPrice())
                .stock(catalog.getStock())
                .createdAt(catalog.getCreatedAt())
                .build();
    }
}
