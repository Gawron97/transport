package com.rekrutacja.transport.utils.driver.exceptions;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DriverExceptionAdrive {

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleDrierNotFoundException(DriverNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDriverError().getMessage()));
    }

    @ExceptionHandler(DriverNeedGarageException.class)
    public ResponseEntity<ErrorInfo> handleDriverNeedGarageException(DriverNeedGarageException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDriverError().getMessage()));
    }

}
