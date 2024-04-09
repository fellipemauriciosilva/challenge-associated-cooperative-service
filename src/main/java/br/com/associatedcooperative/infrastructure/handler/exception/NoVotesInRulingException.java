package br.com.associatedcooperative.infrastructure.handler.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class NoVotesInRulingException extends RuntimeException implements Serializable {
    public NoVotesInRulingException(String message) {
        super(message);
    }
}
