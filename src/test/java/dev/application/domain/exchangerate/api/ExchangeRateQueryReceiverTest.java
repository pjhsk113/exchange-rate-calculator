package dev.application.domain.exchangerate.api;

import dev.application.domain.exchangerate.application.CurrencyInformationManager;
import dev.application.domain.exchangerate.domain.ExchangeRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExchangeRateQueryReceiver.class)
class ExchangeRateQueryReceiverTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyInformationManager currencyInformationManager;

    @DisplayName("환율 정보 조회 시 ExchangeRateResponse 객체에 올바른 응답이 담겨 반환된다.")
    @ParameterizedTest
    @MethodSource("exchangeRateData")
    void when_exchange_rate_look_up(ExchangeRate exchangeRate) throws Exception {
        // given
        given(currencyInformationManager.getSupportedExchangeRate()).willReturn(exchangeRate);

        // when
        ResultActions result = mockMvc.perform(get("/exchange-rate/info")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.usdkrw").value(exchangeRate.getUsdkrw()))
                .andExpect(jsonPath("$.usdjpy").value(exchangeRate.getUsdjpy()))
                .andExpect(jsonPath("$.usdphp").value(exchangeRate.getUsdphp()));
    }

    @DisplayName("수취 금액 조회 요청 시 ExchangeAmountResponse 객체에 올바른 응답이 담겨 반환된다.")
    @Test
    void when_exchange_amount_calculate() throws Exception {
        // given
        BigDecimal expectAmount = BigDecimal.valueOf(117908.9912);
        given(currencyInformationManager.calculateAmount(any())).willReturn(expectAmount);

        // when
        ResultActions result = mockMvc.perform(get("/exchange-rate/amount")
                        .param("recipientCountry", "usdkrw")
                        .param("remittanceAmount", "100"))
                        .andDo(print());

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.remittanceAmount").value(expectAmount));
    }

    @DisplayName("수취 국가 필드가 누락되면 BindException이 발생한다.")
    @Test
    void when_missing_recipient_country() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/exchange-rate/amount")
                        .param("recipientCountry", "")
                        .param("remittanceAmount", "100"))
                .andDo(print());

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(results ->
                        assertThat(results.getResolvedException()).isInstanceOf(BindException.class));
    }

    @DisplayName("송금액이 10_000 보다 큰 경우 BindException이 발생한다.")
    @Test
    void when_remittance_amount_greater_than_10000() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/exchange-rate/amount")
                        .param("recipientCountry", "usdkrw")
                        .param("remittanceAmount", "199999"))
                .andDo(print());

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(results ->
                        assertThat(results.getResolvedException()).isInstanceOf(BindException.class));
    }

    @DisplayName("송금액이 0 보다 작은 경우 BindException이 발생한다.")
    @Test
    void when_remittance_amount_less_than_0() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/exchange-rate/amount")
                        .param("recipientCountry", "usdkrw")
                        .param("remittanceAmount", "0"))
                .andDo(print());

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(results ->
                        assertThat(results.getResolvedException()).isInstanceOf(BindException.class));
    }


    private static Stream<Arguments> exchangeRateData() {
        return Stream.of(
                Arguments.of(new ExchangeRate(BigDecimal.valueOf(1179.089912), BigDecimal.valueOf(113.932499), BigDecimal.valueOf(50.132497)))
        );
    }
}