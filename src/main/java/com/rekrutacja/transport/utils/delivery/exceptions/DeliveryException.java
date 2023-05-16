package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.management.relation.RoleInfo;

@AllArgsConstructor
@Getter
public class DeliveryException extends RuntimeException {

    protected DeliveryError deliveryError;
    protected HttpStatus status;

}
