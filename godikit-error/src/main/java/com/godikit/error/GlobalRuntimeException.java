package com.godikit.error;

import com.godikit.error.code.BaseErrorCode;
import com.godikit.error.code.ErrorCode;
import com.godikit.error.code.ErrorCodeRegistry;
import lombok.Getter;

import static com.godikit.error.utils.StringUtils.defaultIfNull;

@Getter
public class GlobalRuntimeException extends RuntimeException {

    private final String errorSource;

    private final ErrorCode errorCode;

    public GlobalRuntimeException(String message, Throwable cause) {
        super(defaultIfNull(message, BaseErrorCode.SYSTEM_ERROR.getDescription()), cause);
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalRuntimeException(String message) {
        super(defaultIfNull(message, BaseErrorCode.SYSTEM_ERROR.getDescription()));
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalRuntimeException(Throwable cause) {
        super(cause);
        this.errorSource = "unknown";
        this.errorCode = BaseErrorCode.SYSTEM_ERROR;
    }

    public GlobalRuntimeException(String message, Throwable cause, ErrorCode errorCode) {
        super(defaultIfNull(message, errorCode.getDescription()), cause);
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalRuntimeException(String message, ErrorCode errorCode) {
        super(defaultIfNull(message, errorCode.getDescription()));
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalRuntimeException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorSource = "unknown";
        this.errorCode = errorCode;
    }

    public GlobalRuntimeException(String message, Throwable cause, String code) {
        super(defaultIfNull(message, ErrorCodeRegistry.getInstance().getByCode(code, BaseErrorCode.SYSTEM_ERROR).getDescription()), cause);
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().getByCode(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public GlobalRuntimeException(String message, String code) {
        super(defaultIfNull(message, ErrorCodeRegistry.getInstance().getByCode(code, BaseErrorCode.SYSTEM_ERROR).getDescription()));
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().getByCode(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public GlobalRuntimeException(Throwable cause, String code) {
        super(cause);
        errorSource = "unknown";
        errorCode = ErrorCodeRegistry.getInstance().getByCode(code, BaseErrorCode.SYSTEM_ERROR);
    }

    public static GlobalException toGlobalException(GlobalRuntimeException exception) {
        if (exception == null) {
            return null;
        }
        return new GlobalException(
                exception.getMessage(),
                exception.getCause(),
                exception.getErrorCode()
        );
    }
}