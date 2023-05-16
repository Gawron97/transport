package com.rekrutacja.transport.utils.delivery.exceptions;

import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.utils.delivery.exceptions.DeliveryError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class DriverAndTruckNotInSameGaragesException extends DeliveryException {

    public DriverAndTruckNotInSameGaragesException(DeliveryError deliveryError, HttpStatus status) {
        super(deliveryError, status);
    }

}
