package dev.application.domain.exchangerate.application;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;
import dev.application.infra.currencylayer.CurrencyLayerApi;
import dev.application.infra.currencylayer.dto.CurrencyLayerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyInformationManager {

    private final CurrencyLayerApi currencyLayerApi;

    public ExchangeRate getSupportedExchangeRate() {
        CurrencyLayerApiResponse response = currencyLayerApi.getExchangeRate();
        return CurrencyLayerApiResponse.newExchangeRate(response);
    }

    public BigDecimal calculateAmount(ExchangeAmountRequest request) {
        ExchangeRate exchangeRate = getSupportedExchangeRate();
        return CurrencyOperator.calculate(request, exchangeRate);
    }
}
