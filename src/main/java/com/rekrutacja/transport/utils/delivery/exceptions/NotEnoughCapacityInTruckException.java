package com.rekrutacja.transport.utils.delivery.exceptions;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class NotEnoughCapacityInTruckException extends DeliveryException {

    public NotEnoughCapacityInTruckException(DeliveryError deliveryError, HttpStatus status) {
        super(deliveryError, status);
    }

}
