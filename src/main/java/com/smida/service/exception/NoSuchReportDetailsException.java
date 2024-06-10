package com.smida.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchReportDetailsException extends RuntimeException{
    public NoSuchReportDetailsException() {
        super();
    }
    public NoSuchReportDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchReportDetailsException(String message) {
        super(message);
    }
    public NoSuchReportDetailsException(Throwable cause) {
        super(cause);
    }
}
