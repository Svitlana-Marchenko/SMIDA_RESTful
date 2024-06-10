package com.smida.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchReportException extends RuntimeException{
    public NoSuchReportException() {
        super();
    }
    public NoSuchReportException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchReportException(String message) {
        super(message);
    }
    public NoSuchReportException(Throwable cause) {
        super(cause);
    }
}

