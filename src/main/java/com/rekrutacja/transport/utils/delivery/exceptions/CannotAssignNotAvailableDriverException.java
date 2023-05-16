package com.rekrutacja.transport.utils.delivery.exceptions;

import com.rekrutacja.transport.utils.driver.exceptions.DriverError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CannotAssignNotAvailableDriverException extends DeliveryException {

    public CannotAssignNotAvailableDriverException(DeliveryError deliveryError, HttpStatus status) {
        super(deliveryError, status);
    }

}
