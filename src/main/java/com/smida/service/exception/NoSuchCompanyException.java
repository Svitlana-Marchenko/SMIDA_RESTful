package com.smida.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchCompanyException extends RuntimeException{
    public NoSuchCompanyException() {
        super();
    }
    public NoSuchCompanyException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchCompanyException(String message) {
        super(message);
    }
    public NoSuchCompanyException(Throwable cause) {
        super(cause);
    }
}
