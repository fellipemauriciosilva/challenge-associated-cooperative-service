package br.com.associatedcooperative.infrastructure.handler;

import br.com.associatedcooperative.infrastructure.handler.exception.DateTimeValidationException;
import br.com.associatedcooperative.infrastructure.handler.exception.MeetingValidationException;
import br.com.associatedcooperative.infrastructure.handler.exception.NoVotesInRulingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleBadRequest(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getFieldErrors()
                        .stream()
                        .map(ErrorMessage::new)
                        .toList());
    }

    @ExceptionHandler(DateTimeValidationException.class)
    public ResponseEntity<ErrorMessage> handleDateTimeValidationException(DateTimeValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(MeetingValidationException.class)
    public ResponseEntity<ErrorMessage> handleMeetingValidationException(MeetingValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(NoVotesInRulingException.class)
    public ResponseEntity<ErrorMessage> handleNoVotesInRulingException(NoVotesInRulingException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }
}
