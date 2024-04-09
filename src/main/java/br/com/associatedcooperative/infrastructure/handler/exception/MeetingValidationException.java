package br.com.associatedcooperative.infrastructure.handler.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class MeetingValidationException extends RuntimeException implements Serializable {
    public MeetingValidationException(String message) {
        super(message);
    }
}
