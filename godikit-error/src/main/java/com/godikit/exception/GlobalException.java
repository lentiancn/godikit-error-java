package com.godikit.exception;

import com.godikit.error.code.BaseErrorCode;
import com.godikit.error.code.ErrorCode;
import com.godikit.error.code.ErrorCodeRegistry;
import lombok.Getter;

import java.util.Optional;

import static com.godikit.error.utils.StringUtils.defaultIfNull;

@Getter
public class GlobalException extends Exception {

    private final String errorSource;

    private final ErrorCode errorCode;

    public GlobalException() {
        super();
        errorSource = "unknown";
        errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalException(String message) {
        super(defaultIfNull(message, BaseErrorCode.SYSTEM_ERROR.getDescription()));
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalException(String message, Throwable cause) {
        super(defaultIfNull(message, BaseErrorCode.SYSTEM_ERROR.getDescription()), cause);
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalException(Throwable cause) {
        super(cause);
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalException(ErrorCode errorCode) {
        super(Optional.ofNullable(errorCode).map(ErrorCode::getDescription).orElse(null));
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalException(String message, ErrorCode errorCode) {
        super(defaultIfNull(message, Optional.ofNullable(errorCode).map(ErrorCode::getDescription).orElse(null)));
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalException(String message, Throwable cause, ErrorCode errorCode) {
        super(defaultIfNull(message, Optional.ofNullable(errorCode).map(ErrorCode::getDescription).orElse(null)), cause);
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalException(String message, String code) {
        super(defaultIfNull(message, ErrorCodeRegistry.getInstance().get(code, BaseErrorCode.SYSTEM_ERROR).getDescription()));
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().get(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public GlobalException(String message, Throwable cause, String code) {
        super(defaultIfNull(message, ErrorCodeRegistry.getInstance().get(code, BaseErrorCode.SYSTEM_ERROR).getDescription()), cause);
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().get(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public GlobalException(Throwable cause, String code) {
        super(cause);
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().get(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public static GlobalRuntimeException toGlobalRuntimeException(GlobalException exception) {
        if (exception == null) {
            return null;
        }
        return new GlobalRuntimeException(
                exception.getMessage(),
                exception.getCause(),
                exception.getErrorCode()
        );
    }

    @Override
    public String toString() {
        String errorCodeStr;

        if (errorCode != null) {
            errorCodeStr = errorCode.getCode() + " " + errorCode.getDescription();
        } else {
            errorCodeStr = "unknown";
        }

        return "[" + errorSource + "]" +
                " [" + errorCodeStr + "] " +
                super.toString();
    }
}