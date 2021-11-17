package dev.application.domain.exchangerate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeRate {
    private BigDecimal usdkrw;
    private BigDecimal usdjpy;
    private BigDecimal usdphp;
}
