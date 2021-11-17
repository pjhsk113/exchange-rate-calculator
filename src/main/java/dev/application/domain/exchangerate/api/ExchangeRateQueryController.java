package dev.application.domain.exchangerate.api;

import dev.application.domain.exchangerate.application.CurrencyInformationManager;
import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;
import dev.application.domain.exchangerate.dto.response.ExchangeAmountResponse;
import dev.application.domain.exchangerate.dto.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange-rate")
public class ExchangeRateQueryController {
    private final CurrencyInformationManager currencyInformationManager;

    @GetMapping("/info")
    public ResponseEntity<ExchangeRateResponse> getExchangeRateInformation() {
        ExchangeRate exchangeRate = currencyInformationManager.getSupportedExchangeRate();
        return ResponseEntity.ok(ExchangeRateResponse.of(exchangeRate));
    }

    @GetMapping("/amount")
    public ResponseEntity<ExchangeAmountResponse> getExchangeAmount(@Valid ExchangeAmountRequest request) {
        BigDecimal exchangeAmount = currencyInformationManager.calculateAmount(request);
        return ResponseEntity.ok(ExchangeAmountResponse.of(exchangeAmount));
    }
}