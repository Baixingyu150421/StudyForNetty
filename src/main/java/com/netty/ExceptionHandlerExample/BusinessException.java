package com.netty.ExceptionHandlerExample;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
