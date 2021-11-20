package dev.application.domain.exchangerate.application;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;
import dev.application.infrastructure.currencylayer.CurrencyLayerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyInformationManager {

    private final CurrencyLayerApi currencyLayerApi;

    public ExchangeRate getSupportedExchangeRate() {
        return currencyLayerApi.getExchangeRate();
    }

    public BigDecimal calculateAmount(ExchangeAmountRequest request) {
        ExchangeRate exchangeRate = getSupportedExchangeRate();
        return CurrencyOperator.calculate(request, exchangeRate);
    }
}
