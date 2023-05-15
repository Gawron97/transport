package com.rekrutacja.transport.utils.delivery.exceptions;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeliveryExceptionAdvice {

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(DeliveryNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

}
