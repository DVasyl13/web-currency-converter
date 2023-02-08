package com.example.app.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class CurrencyRate {
    private String name;
    private JsonNode rates;
}
