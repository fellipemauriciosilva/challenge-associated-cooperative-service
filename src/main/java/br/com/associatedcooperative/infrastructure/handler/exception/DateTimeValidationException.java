package br.com.associatedcooperative.infrastructure.handler.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class DateTimeValidationException extends RuntimeException implements Serializable {
    public DateTimeValidationException(String message) {
        super(message);
    }
    public DateTimeValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
