package com.axis.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequestValidationException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public RequestValidationException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public RequestValidationException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
