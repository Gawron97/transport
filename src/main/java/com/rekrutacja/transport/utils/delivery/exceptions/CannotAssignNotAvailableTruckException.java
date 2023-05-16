package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CannotAssignNotAvailableTruckException extends DeliveryException {

    public CannotAssignNotAvailableTruckException(DeliveryError deliveryError, HttpStatus status) {
        super(deliveryError, status);
    }

}
