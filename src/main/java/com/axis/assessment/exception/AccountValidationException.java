package com.axis.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountValidationException extends RuntimeException {

    public AccountValidationException(String message) {
        super(message);
    }
}
