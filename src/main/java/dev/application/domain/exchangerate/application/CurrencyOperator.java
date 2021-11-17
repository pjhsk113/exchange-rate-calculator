package dev.application.domain.exchangerate.application;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum CurrencyOperator {
    KRW("usdkrw", (amount, exchangeRate) -> amount.multiply(exchangeRate.getUsdkrw())),
    JPY("usdjpy", (amount, exchangeRate) -> amount.multiply(exchangeRate.getUsdjpy())),
    PHP("usdphp", (amount, exchangeRate) -> amount.multiply(exchangeRate.getUsdphp())),
    EMPTY("none", (amount, exchangeRate) -> BigDecimal.ZERO);

    private final String exchangeCode;
    private final BiFunction<BigDecimal, ExchangeRate, BigDecimal> expression;

    CurrencyOperator(String exchangeCode, BiFunction<BigDecimal, ExchangeRate, BigDecimal> expression) {
        this.exchangeCode = exchangeCode;
        this.expression = expression;
    }

    public static BigDecimal calculate(ExchangeAmountRequest request, ExchangeRate rate) {
        return findCountry(request.getRecipientCountry())
                .expression.apply(BigDecimal.valueOf(request.getRemittanceAmount()), rate);
    }

    private static CurrencyOperator findCountry(String recipientCountry) {
        return Arrays.stream(values())
                .filter(country -> country.exchangeCode.equals(recipientCountry))
                .findFirst()
                .orElse(EMPTY); // TODO exception처리하도록 변경해야함
    }
}
