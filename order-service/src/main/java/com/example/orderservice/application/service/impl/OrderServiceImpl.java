package com.example.orderservice.application.service.impl;

import com.example.orderservice.application.service.OrderService;
import com.example.orderservice.infrastructure.dto.QuantityUpdateDTO;
import com.example.orderservice.infrastructure.producer.KafkaProducer;
import com.example.orderservice.infrastructure.producer.OrderProducer;
import com.example.orderservice.persistence.domain.Order;
import com.example.orderservice.persistence.repository.JpaOrderRepository;
import com.example.orderservice.presentation.dto.request.CreateOrderRequest;
import com.example.orderservice.presentation.dto.response.ReadOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final JpaOrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final KafkaProducer kafkaProducer;

    @Override
    public void createOrder(CreateOrderRequest request, String userId) {
        Order order = request.toEntity(userId);
        orderProducer.send("orders", order.toPayload());
        kafkaProducer.send("example-catalog-topic", QuantityUpdateDTO.from(order));
    }

    @Override
    public ReadOrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 주문이 없습니다"));

        return ReadOrderResponse.fromEntity(order);
    }

    @Override
    public List<ReadOrderResponse> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream().map(ReadOrderResponse::fromEntity).toList();
    }
}
