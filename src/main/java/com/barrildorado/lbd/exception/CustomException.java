package com.barrildorado.lbd.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CustomException extends RuntimeException {
    // Constructor que acepta un mensaje de error
    public CustomException(String message) {
        super(message);
    }
}