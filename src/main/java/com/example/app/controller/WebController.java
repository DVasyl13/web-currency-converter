package com.example.app.controller;

import com.example.app.service.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/converter")
@RequiredArgsConstructor
public class WebController {
    private final ConverterService converterService;

    @GetMapping
    public String getPage(Model model) {
        var rates = converterService.getRates();
        model.addAttribute("rates", rates);
        return "converter";
    }
}
