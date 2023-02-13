package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class WebCurrencyConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCurrencyConverterApplication.class, args);
    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000) // each 12 hours will be refreshed
    @CacheEvict("allRates")
    public void refreshCache() {
        log.info("Refreshing Currency cache...");
    }

}
