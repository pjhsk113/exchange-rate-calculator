package dev.application.domain.exchangerate.exception;

import dev.application.global.error.exception.BusinessException;
import dev.application.global.error.exception.ErrorCode;

public class UnsupportedRecipientCountryException extends BusinessException {

    public UnsupportedRecipientCountryException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public UnsupportedRecipientCountryException(ErrorCode errorCode) {
        super(errorCode);
    }
}
