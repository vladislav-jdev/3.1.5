package ru.kata.spring.boot_security.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kata.spring.boot_security.demo.model.Errors.NotFoundError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<NotFoundError> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new NotFoundError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}