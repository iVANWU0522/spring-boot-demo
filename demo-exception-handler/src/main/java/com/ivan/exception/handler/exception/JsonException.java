package com.ivan.exception.handler.exception;

import com.ivan.exception.handler.constant.Status;
import lombok.Getter;

@Getter
public class JsonException extends BaseException {
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}