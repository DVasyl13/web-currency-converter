package com.example.app.service;

import com.example.app.pojo.CurrencyRate;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ConverterService {
    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Cacheable("allRates")
    public Map<String, Double> getRates() {
        var response = callApiForAllCurrencies();
        return Objects.requireNonNull(response.getBody()).getRates();
    }

    private ResponseEntity<CurrencyRate> callApiForAllCurrencies() {
        var url = apiUrl + "/latest?base=USD&apikey="+apiKey;
        return restTemplate.getForEntity(url, CurrencyRate.class);
    }

}
