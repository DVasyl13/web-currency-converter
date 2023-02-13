package com.example.app.controller.api;

import com.example.app.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/currency-rates")
public class CurrencyController {
    @Autowired
    private ConverterService converterService;

    @GetMapping
    public Map<String, Double> getCurrencyRates () {
        return converterService.getRates();
    }
}
