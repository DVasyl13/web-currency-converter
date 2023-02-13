package com.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/converter")
@RequiredArgsConstructor
public class WebController {
    @GetMapping
    public String getPage() {
        return "converter";
    }
}
