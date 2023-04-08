package com.ivan.exception.handler.constant;

import lombok.Getter;

@Getter
public enum Status {
    OK(200, "OK"),
    UNKNOWN_ERROR(500, "Unknown Error");

    private final Integer code;
    private final String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}