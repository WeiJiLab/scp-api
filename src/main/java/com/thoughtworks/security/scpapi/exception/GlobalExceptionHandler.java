package com.thoughtworks.security.scpapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleDomainNotFoundException(NotFoundException exception) {
        Error error = buildError(exception.getMessage());
        return new ResponseEntity(error, NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<Error> handleDomainDuplicatedException(DuplicatedException exception) {
        Error error = buildError(exception.getMessage());
        return new ResponseEntity(error, CONFLICT);
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<Error> handleDomainDuplicatedException(UploadFileException exception) {
        Error error = buildError(exception.getMessage());
        return new ResponseEntity(error, INTERNAL_SERVER_ERROR);
    }

    private Error buildError(String message) {
        return Error.builder()
                .message(message)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
