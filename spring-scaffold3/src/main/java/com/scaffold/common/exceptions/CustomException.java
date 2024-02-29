package com.scaffold.common.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    public HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return (this.httpStatus == null) ? HttpStatus.INTERNAL_SERVER_ERROR : this.httpStatus;
    }
}