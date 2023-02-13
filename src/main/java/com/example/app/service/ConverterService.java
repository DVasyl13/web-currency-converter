package com.example.app.service;

import com.example.app.pojo.CurrencyRate;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;


@Slf4j
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
        log.info("I got response!");
        return Objects.requireNonNull(response.getBody()).getRates();
    }

    private ResponseEntity<CurrencyRate> callApiForAllCurrencies() {
        var url = apiUrl + "/latest?base=USD&apikey="+apiKey;
        log.info("I am calling API...");
        return restTemplate.getForEntity(url, CurrencyRate.class);
    }

}
