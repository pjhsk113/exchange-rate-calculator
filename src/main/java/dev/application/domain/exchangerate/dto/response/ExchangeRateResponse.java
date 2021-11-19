package dev.application.domain.exchangerate.dto.response;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRateResponse {
    private BigDecimal usdkrw;
    private BigDecimal usdjpy;
    private BigDecimal usdphp;

    private ExchangeRateResponse(BigDecimal usdkrw, BigDecimal usdjpy, BigDecimal usdphp) {
        this.usdkrw = usdkrw;
        this.usdjpy = usdjpy;
        this.usdphp = usdphp;
    }

    public static ExchangeRateResponse of(ExchangeRate exchangeRate) {
        return new ExchangeRateResponse(
                exchangeRate.getUsdkrw(),
                exchangeRate.getUsdjpy(),
                exchangeRate.getUsdphp()
        );
    }
}
