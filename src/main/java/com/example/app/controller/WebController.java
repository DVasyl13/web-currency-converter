package com.example.app.controller;

import com.example.app.service.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/converter")
@RequiredArgsConstructor
public class WebController {
    private final ConverterService converterService;

    @GetMapping
    public String getPage() {
        return "converter";
    }

    @PostMapping
    public String getResult(@RequestParam("amount") float amount,
                          @RequestParam("fromCurrency") String fromCurrency,
                          @RequestParam("toCurrency") String toCurrency, Model model) {
        System.out.println(amount + " " + fromCurrency + " - " + toCurrency);
        var result = converterService.convertCurrency(amount, fromCurrency, toCurrency);
        model.addAttribute("result", result);
        System.out.println(result);
        return "converter";
    }
}
