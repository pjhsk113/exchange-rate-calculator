package dev.application.infra.currencylayer;

import dev.application.infra.currencylayer.dto.CurrencyLayerApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyLayerApi {
    private static final String URI = "/live?access_key=";

    private final String accessKey;
    private final RestTemplate currencyLayerTemplate;

    public CurrencyLayerApi(@Value("${api.accessKey}") String accessKey, RestTemplate currencyLayerTemplate) {
        this.accessKey = accessKey;
        this.currencyLayerTemplate = currencyLayerTemplate;
    }

    public CurrencyLayerApiResponse getExchangeRate() {
        return currencyLayerTemplate.getForObject(URI + accessKey, CurrencyLayerApiResponse.class);
    }
}
