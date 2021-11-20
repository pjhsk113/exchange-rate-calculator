package dev.application.infrastructure.currencylayer.exception;

import dev.application.global.error.exception.ErrorCode;

public class ExternalApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExternalApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ExternalApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
