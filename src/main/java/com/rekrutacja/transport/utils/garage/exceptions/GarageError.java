package com.rekrutacja.transport.utils.garage.exceptions;

import lombok.Getter;

@Getter
public enum GarageError {

    GARAGE_NOT_FOUND("Garage not found");

    private String message;

    GarageError(String message) {
        this.message = message;
    }


}
