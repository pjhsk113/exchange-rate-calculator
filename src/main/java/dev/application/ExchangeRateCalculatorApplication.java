package dev.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ExchangeRateCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateCalculatorApplication.class, args);
    }

}
