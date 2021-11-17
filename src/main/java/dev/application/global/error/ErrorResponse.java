package dev.application.global.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.application.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detailMessage;

    private ErrorResponse(final ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code, final String message) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.code = code.getCode();
        this.detailMessage = message;
    }

    public static ErrorResponse of(final ErrorCode code, BindingResult result) {
        return new ErrorResponse(code, result.getFieldError().getDefaultMessage());
    }

    public static ErrorResponse of(final ErrorCode code, String details) {
        return new ErrorResponse(code, details);
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }
}
