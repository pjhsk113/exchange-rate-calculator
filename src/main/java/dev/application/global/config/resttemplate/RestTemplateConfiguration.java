package dev.application.global.config.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
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
                .requestFactory(()-> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

}
