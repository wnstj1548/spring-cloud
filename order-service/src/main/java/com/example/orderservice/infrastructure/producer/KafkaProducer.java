package com.example.orderservice.infrastructure.producer;

import com.example.orderservice.commons.exception.ApplicationException;
import com.example.orderservice.commons.exception.payload.ErrorStatus;
import com.example.orderservice.infrastructure.dto.OrderCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, OrderCreatedEvent event) {

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(
                    ErrorStatus.toErrorStatus("json 변경 중 오류가 발생하였습니다.", 500, LocalDateTime.now())
            );
        }

        kafkaTemplate.send(topic, jsonString);
        log.info("kafka message send success");
    }
}
