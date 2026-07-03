package com.godikit.error.code;

import lombok.Getter;

@Getter
public enum BaseErrorCode implements ErrorCode {

    NO_ERROR("000000", "No Error"),

    SYSTEM_ERROR("000010", "System Error"),
    UNKNOWN_ERROR("000011", "Unknown Error"),

    PARAM_ERROR("000020", "Parameter Error"),
    PARAM_MISSING_ERROR("000021", "Parameter Missing Error"),
    PARAM_INVALID_ERROR("000022", "Parameter Invalid Error");

    private final String name;
    private final String code;
    private final String description;

    BaseErrorCode(String code, String description) {
        this.name = name();
        this.code = code;
        this.description = description;
    }
}