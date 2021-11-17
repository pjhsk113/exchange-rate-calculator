package dev.application.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {
    private static final String CURRENCY_LAYER_URL = "http://api.currencylayer.com";

    private final RestTemplateBuilder templateBuilder;

    @Bean
    public RestTemplate currencyLayerTemplate() {
        return templateBuilder.rootUri(CURRENCY_LAYER_URL)
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

}
