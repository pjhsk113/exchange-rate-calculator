package dev.application.domain.exchangerate.application;

import dev.application.domain.exchangerate.domain.ExchangeRate;
import dev.application.domain.exchangerate.dto.request.ExchangeAmountRequest;
import dev.application.domain.exchangerate.exception.UnsupportedRecipientCountryException;
import dev.application.infrastructure.currencylayer.CurrencyLayerApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CurrencyInformationManagerTest {

    @Mock
    private CurrencyLayerApi currencyLayerApi;

    @BeforeEach
    void setUp() {
        ExchangeRate exchangeRate = new ExchangeRate(BigDecimal.valueOf(1179.089912), BigDecimal.valueOf(113.932499), BigDecimal.valueOf(50.132497));
        given(currencyLayerApi.getExchangeRate()).willReturn(exchangeRate);
    }

    @DisplayName("송금액 계산에 성공하면 올바른 금액을 반환한다.")
    @Test
    void should_remittance_calculation_success() {
        // given
        ExchangeAmountRequest request = new ExchangeAmountRequest("usdkrw", 100);
        ExchangeRate exchangeRate = currencyLayerApi.getExchangeRate();

        // when
        BigDecimal result = CurrencyOperator.calculate(request, exchangeRate);

        // then
        assertThat(exchangeRate.getUsdkrw().multiply(BigDecimal.valueOf(request.getRemittanceAmount()))).isEqualTo(result);
    }

    @DisplayName("지원하지 않는 국가일 경우 UnsupportedRecipientCountryException이 발생한다.")
    @Test
    void non_existent_country() {
        // given
        ExchangeAmountRequest request = new ExchangeAmountRequest("usdkkk", 100);
        ExchangeRate exchangeRate = currencyLayerApi.getExchangeRate();

        // then
        assertThatThrownBy(() ->
                CurrencyOperator.calculate(request, exchangeRate))
                .isInstanceOf(UnsupportedRecipientCountryException.class)
                .hasMessage(request.getRecipientCountry() + "는 지원하지 않는 국가입니다.");
    }
}