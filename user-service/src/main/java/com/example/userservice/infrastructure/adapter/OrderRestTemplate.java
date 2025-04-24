package com.example.userservice.infrastructure.adapter;

import com.example.userservice.presentation.dto.response.ReadOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderRestTemplate {

    private final RestTemplate restTemplate;
    private final Environment env;

    public List<ReadOrderResponse> getByUserId(String userId) {

        String orderUrl = String.format(env.getProperty("order_service.url"), userId);

        return restTemplate.exchange(orderUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ReadOrderResponse>>() {
                        }).getBody();
    }
}
