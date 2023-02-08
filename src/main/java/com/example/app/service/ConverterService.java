package com.example.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ConverterService {
    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public Double getConvertedAmount(double amount,String fromCurrency, String toCurrency) {
        var response = callApi(fromCurrency, toCurrency);
        var rate = Objects.requireNonNull(response.getBody()).get("rates");
        return getAmount(amount, rate.get(toCurrency).asDouble());
    }

    private ResponseEntity<JsonNode> callApi(String fromCurrency, String toCurrency) {
        var url = apiUrl + "/latest?symbols="+toCurrency+"&base="+fromCurrency+"&apikey="+apiKey;
        return restTemplate.getForEntity(url, JsonNode.class);
    }

    private Double getAmount(double amount, double rate) {
        return amount * rate;
    }

}
