package dev.application.domain.exchangerate.dto.response;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeRateResponse {
    private BigDecimal usdkrw;
    private BigDecimal usdjpy;
    private BigDecimal usdphp;

    public static ExchangeRateResponse of(ExchangeRate exchangeRate) {
        return new ExchangeRateResponse(
                exchangeRate.getUsdkrw(),
                exchangeRate.getUsdjpy(),
                exchangeRate.getUsdphp()
        );
    }
}
