package com.rekrutacja.transport.utils.trucks.exceptions;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TruckExceptionAdvice {

    @ExceptionHandler(TruckNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleTruckNotFoundException(TruckNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getTruckError().getMessage()));
    }

    @ExceptionHandler(TruckNeedGarageException.class)
    public ResponseEntity<ErrorInfo> handleTruckNotFoundException(TruckNeedGarageException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getTruckError().getMessage()));
    }

}
