package com.example.app.service;

import com.example.app.dto.CurrencyRate;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    private CurrencyRate currentCurrency;

    private boolean checkIfCurrentCurrencyCouldBeAccessed(String fromCurrency) {
        if(this.currentCurrency != null){
            return !currentCurrency.getName().equals(fromCurrency);
        }
        currentCurrency = new CurrencyRate();
        return true;
    }

    public Double getConvertedAmount(double amount, String fromCurrency, String toCurrency) {
        if(checkIfCurrentCurrencyCouldBeAccessed(fromCurrency)) {
            var response = callApi(fromCurrency, toCurrency);
            currentCurrency.setName(fromCurrency);
            currentCurrency.setRates(response.getBody().get("rates"));
        }
        return getAmount(amount, toCurrency);
    }

    private ResponseEntity<JsonNode> callApi(String fromCurrency, String toCurrency) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        var attributes = Map.of("symbols", toCurrency, "base", fromCurrency);
        return restTemplate.exchange(apiUrl + "/latest?",
                HttpMethod.GET,
                entity, JsonNode.class, attributes);
    }

    private Double getAmount(double amount, String toCurrency) {
        return amount * currentCurrency.getRates().get(toCurrency).asDouble();
    }

}
