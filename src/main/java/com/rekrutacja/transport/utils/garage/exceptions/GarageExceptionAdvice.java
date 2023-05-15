package com.rekrutacja.transport.utils.garage.exceptions;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GarageExceptionAdvice {

    @ExceptionHandler(GarageNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleGarageNotFoundException(GarageNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getGarageError().getMessage()));
    }

    @ExceptionHandler(CannotDeleteGarageWithTrucksException.class)
    public ResponseEntity<ErrorInfo> handleGarageNotFoundException(CannotDeleteGarageWithTrucksException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getGarageError().getMessage()));
    }

    @ExceptionHandler(CannotDeleteGarageWithDriversException.class)
    public ResponseEntity<ErrorInfo> handleGarageNotFoundException(CannotDeleteGarageWithDriversException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getGarageError().getMessage()));
    }

}
