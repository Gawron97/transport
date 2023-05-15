package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.Getter;

@Getter
public enum DeliveryError {

    DELIVERY_NOT_FOUND("Delivery not found"),
    DELIVERY_NEED_TRUCK("Delivery need truck"),
    DELIVERY_NEED_DRIVER("Delivery need driver"),
    CANNOT_ASSIGN_NOT_AVAILABLE_DRIVER("Not available driver cannot be assigned to delivery"),
    CANNOT_ASSIGN_NOT_AVAILABLE_TRUCK("Not available truck cannot be assigned to delivery");

    private String message;

    DeliveryError(String message) {
        this.message = message;
    }

}
