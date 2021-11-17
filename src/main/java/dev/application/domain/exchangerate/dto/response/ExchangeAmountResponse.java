package dev.application.domain.exchangerate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeAmountResponse {
    private BigDecimal remittanceAmount;

    public static ExchangeAmountResponse of(BigDecimal amount) {
        return new ExchangeAmountResponse(amount);
    }
}
