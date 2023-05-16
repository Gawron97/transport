package com.rekrutacja.transport.utils.driver.exceptions;

import lombok.Getter;

@Getter
public enum DriverError {

    DRIVER_NOT_FOUND("Driver not found"),
    DRIVER_HAVE_TO_HAS_GARAGE("Driver have to has garage");

    private final String message;

    DriverError(String message) {
        this.message = message;
    }

}
