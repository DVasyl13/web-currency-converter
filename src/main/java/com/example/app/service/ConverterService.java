package com.example.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConverterService {
    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;



    public Double convertCurrency(float amount, String fromCurrency, String toCurrency) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        var attributes = Map.of("symbols", toCurrency, "base", fromCurrency);

        var response = restTemplate.exchange(apiUrl + "/latest?",
                HttpMethod.GET,
                entity, JsonNode.class, attributes);
        var body = response.getBody().get("rates");
        var a = body.get(toCurrency);
        System.out.println(a.asDouble());
        return (amount * a.asDouble());
    }
}
