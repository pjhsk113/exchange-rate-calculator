package dev.application.domain.exchangerate.application;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.infra.currencylayer.CurrencyLayerApi;
import dev.application.infra.currencylayer.dto.CurrencyLayerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyInformationManager {

    private final CurrencyLayerApi currencyLayerApi;

    public ExchangeRate getSupportedExchangeRate() {
        CurrencyLayerApiResponse response = currencyLayerApi.getExchangeRate();
        return CurrencyLayerApiResponse.newExchangeRate(response);
    }

}
