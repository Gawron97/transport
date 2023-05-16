package com.rekrutacja.transport.utils.delivery.exceptions;

import lombok.Getter;

@Getter
public enum DeliveryError {

    DELIVERY_NOT_FOUND("Delivery not found"),
    DELIVERY_NEED_TRUCK("Delivery need truck"),
    DELIVERY_NEED_DRIVER("Delivery need driver"),
    CANNOT_ASSIGN_NOT_AVAILABLE_DRIVER("Not available driver cannot be assigned to delivery"),
    CANNOT_ASSIGN_NOT_AVAILABLE_TRUCK("Not available truck cannot be assigned to delivery"),
    NOT_ENOUGH_CAPACITY_IN_TRUCK("Truck has to low capacity for this item"),
    DRIVER_AND_TRUCK_HAVE_TO_BE_IN_THE_SAME_GARAGE("Driver and truck have to be in the same garage");

    private final String message;

    DeliveryError(String message) {
        this.message = message;
    }

}
