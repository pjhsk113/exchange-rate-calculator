package dev.application.global.config.cache;

import lombok.Getter;

@Getter
public enum CacheTypes {
    EXCHANGE_RATE("exchange_rate", 30 * 60, 10_000);

    private String cacheName;
    private int expiredAfterWrite;
    private int maximumSize;

    CacheTypes(String cacheName, int expiredAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expiredAfterWrite = expiredAfterWrite;
        this.maximumSize = maximumSize;
    }
}
