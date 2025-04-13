package com.example.orderservice.persistence.repository;

import com.example.orderservice.persistence.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(String userId);
}
