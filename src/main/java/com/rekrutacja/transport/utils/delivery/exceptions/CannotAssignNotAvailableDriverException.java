package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CannotAssignNotAvailableDriverException extends RuntimeException {

    private DeliveryError deliveryError;
    private HttpStatus status;

}