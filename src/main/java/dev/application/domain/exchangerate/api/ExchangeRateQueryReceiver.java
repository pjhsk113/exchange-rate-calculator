package dev.application.domain.exchangerate.api;

import dev.application.domain.exchangerate.application.CurrencyInformationManager;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;
import dev.application.domain.exchangerate.dto.response.ExchangeAmountResponse;
import dev.application.domain.exchangerate.dto.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange-rate")
public class ExchangeRateQueryReceiver {
    private final CurrencyInformationManager currencyInformationManager;

    @GetMapping("/info")
    public ResponseEntity<ExchangeRateResponse> getExchangeRateInformation() {
        return ResponseEntity.ok(
                ExchangeRateResponse.of(currencyInformationManager.getSupportedExchangeRate())
        );
    }

    @GetMapping("/amount")
    public ResponseEntity<ExchangeAmountResponse> getExchangeAmount(@Valid ExchangeAmountRequest request) {
        return ResponseEntity.ok(
                ExchangeAmountResponse.of(currencyInformationManager.calculateAmount(request))
        );
    }
}