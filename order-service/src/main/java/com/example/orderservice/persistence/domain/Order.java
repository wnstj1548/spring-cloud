package com.example.orderservice.persistence.domain;

import com.example.orderservice.infrastructure.dto.Payload;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Order(Long id, Integer quantity, Integer unitPrice, Integer totalPrice, String userId, Long catalogId, LocalDateTime createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.catalogId = catalogId;
        this.createdAt = createdAt;
    }

    public Payload toPayload() {
        return Payload.builder()
                .quantity(quantity)
                .unit_price(unitPrice)
                .user_id(userId)
                .catalog_id(catalogId)
                .total_price(unitPrice * quantity)
                .created_at(LocalDateTime.now().toString())
                .build();
    }
}
