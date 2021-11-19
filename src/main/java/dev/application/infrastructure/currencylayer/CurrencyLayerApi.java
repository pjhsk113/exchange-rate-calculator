package dev.application.infrastructure.currencylayer;

import dev.application.infrastructure.currencylayer.dto.CurrencyLayerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CurrencyLayerApi {
    private static final String URI = "/live?access_key=";

    @Value("${api.accessKey}")
    private String accessKey;
    private final RestTemplate currencyLayerTemplate;
    private final CacheManager cacheManager;

    @Cacheable(cacheNames = "exchange_rate")
    public CurrencyLayerApiResponse getExchangeRate() {
        return currencyLayerTemplate.getForObject(URI + accessKey, CurrencyLayerApiResponse.class);
    }

    // 1분 간격으로 캐시 비우기
    @Scheduled(cron = "0 */1 * * * *")
    public void refreshCache() {
        for (String name : cacheManager.getCacheNames()) {
            Objects.requireNonNull(cacheManager.getCache(name)).clear();
        }
    }
}
