package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DeliveryNotFoundException extends DeliveryException {

    public DeliveryNotFoundException(DeliveryError deliveryError, HttpStatus status) {
        super(deliveryError, status);
    }

}
