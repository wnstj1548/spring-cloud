package com.example.orderservice.infrastructure.producer;

import com.example.orderservice.commons.exception.ApplicationException;
import com.example.orderservice.commons.exception.payload.ErrorStatus;
import com.example.orderservice.infrastructure.dto.*;
import com.example.orderservice.persistence.domain.Order;
import com.example.orderservice.presentation.dto.request.CreateOrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    List<Field> fields = Arrays.asList(
            new Field("int64", true, "catalog_id"),
            new Field("int32", true, "quantity"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price"),
            new Field("string", true, "user_id"),
            new Field("string", true, "created_at"));

    Schema schema = Schema.builder()
            .name("orders")
            .fields(fields)
            .type("struct")
            .optional(false)
            .build();

    public void send(String topic, Payload payload) {

        KafkaCreateOrderDTO kafkaCreateOrderDTO = new KafkaCreateOrderDTO(schema, payload);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(kafkaCreateOrderDTO);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(
                    ErrorStatus.toErrorStatus("json 변경 중 오류가 발생하였습니다.", 500, LocalDateTime.now())
            );
        }

        kafkaTemplate.send(topic, jsonString);
        log.info("kafka message send success");
    }
}
