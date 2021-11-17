package dev.application.domain.exchangerate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ExchangeAmountRequest {
    @NotBlank(message = "수취 국가가 입력되지 않았습니다.")
    private String recipientCountry;

    @Min(value = 1, message = "송금액은 0 이상의 값만 입력할 수 있습니다.")
    @Max(value = 10000, message = "송금액은 10,000 이하의 값만 입력할 수 있습니다.")
    private int remittanceAmount;
}
