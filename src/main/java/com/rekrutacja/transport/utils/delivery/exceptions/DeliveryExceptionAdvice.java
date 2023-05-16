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

    @ExceptionHandler(CannotAssignNotAvailableDriverException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(CannotAssignNotAvailableDriverException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

    @ExceptionHandler(CannotAssignNotAvailableTruckException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(CannotAssignNotAvailableTruckException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

    @ExceptionHandler(DeliveryNeedDriverException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(DeliveryNeedDriverException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

    @ExceptionHandler(DeliveryNeedTruckException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(DeliveryNeedTruckException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

    @ExceptionHandler(NotEnoughCapacityInTruckException.class)
    public ResponseEntity<ErrorInfo> handleDeliveryNotFoundException(NotEnoughCapacityInTruckException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorInfo(ex.getDeliveryError().getMessage()));
    }

}
