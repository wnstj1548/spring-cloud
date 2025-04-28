package com.example.catalogservice.Infrastructure.consumer;

import com.example.catalogservice.commons.exception.ApplicationException;
import com.example.catalogservice.commons.exception.payload.ErrorStatus;
import com.example.catalogservice.persistence.domain.Catalog;
import com.example.catalogservice.persistence.repository.JpaCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KafkaConsumer {

    private final JpaCatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQuantity(String kafkaMessage) {
        log.info("kafkaMessage : {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new ApplicationException(
                    ErrorStatus.toErrorStatus("json 파싱 중 오류가 발생하였습니다.", 500, LocalDateTime.now())
            );
        }

        Long id = ((Integer)map.get("catalogId")).longValue();
        Integer quantity = (Integer) map.get("quantity");

        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorStatus.toErrorStatus("해당하는 카탈로그가 없습니다.", 404, LocalDateTime.now())
                ));

        catalog.updateStock(catalog.getStock() - quantity);
    }
}
