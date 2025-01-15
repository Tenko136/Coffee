package kz.tenko.machine.coffee.controller;

import kz.tenko.machine.coffee.exception.WorkingHoursException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionController {

    @ExceptionHandler(WorkingHoursException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
