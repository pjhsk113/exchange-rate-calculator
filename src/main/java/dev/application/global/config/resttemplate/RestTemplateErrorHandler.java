package dev.application.global.config.resttemplate;

import dev.application.global.error.exception.ErrorCode;
import dev.application.infra.currencylayer.exception.ExternalApiException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        final HttpStatus status = response.getStatusCode();
        final String body = getErrorAsString(response);
        if (body.contains("\"success\": false")) {
            return true;
        }
        return !status.is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        throw new ExternalApiException(ErrorCode.UNABLE_EXTERNAL_API_CALL_ERROR);
    }

    private String getErrorAsString(@NonNull final ClientHttpResponse response) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining());
        }
    }

}
