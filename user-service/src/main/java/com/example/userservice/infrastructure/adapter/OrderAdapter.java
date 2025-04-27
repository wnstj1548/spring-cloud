package com.example.userservice.infrastructure.adapter;

import com.example.userservice.presentation.dto.response.ReadOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderAdapter {

    @GetMapping("/order-service/{userId}/orders")
    List<ReadOrderResponse> getByUserId(@PathVariable String userId);
}
