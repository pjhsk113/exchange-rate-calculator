package dev.application.domain.exchangerate.dto.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeAmountResponse {
    private BigDecimal remittanceAmount;

    private ExchangeAmountResponse(BigDecimal remittanceAmount) {
        this.remittanceAmount = remittanceAmount;
    }

    public static ExchangeAmountResponse of(BigDecimal amount) {
        return new ExchangeAmountResponse(amount);
    }
}
