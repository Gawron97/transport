package com.rekrutacja.transport.utils.delivery.exceptions;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class NotEnoughCapacityInTruckException extends RuntimeException {

    private DeliveryError deliveryError;
    private HttpStatus status;

}
