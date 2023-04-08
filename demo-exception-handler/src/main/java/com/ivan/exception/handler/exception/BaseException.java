package com.ivan.exception.handler.exception;

import com.ivan.exception.handler.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
// generated equals() and hashCode() methods of this class take into account both the fields of this class and its superclass
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(Status status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}