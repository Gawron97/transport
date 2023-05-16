package com.rekrutacja.transport.utils.garage.exceptions;

import lombok.Getter;

@Getter
public enum GarageError {

    GARAGE_NOT_FOUND("Garage not found"),
    CANNOT_DELETE_GARAGE_WITH_TRUCKS_INSIDE("Cannot delete garage with trucks inside, you need to first move these trucks"),
    CANNOT_DELETE_GARAGE_WITH_DRIVERS_INSIDE("Cannot delete garage with drivers inside, you need to first move these drivers");

    private final String message;

    GarageError(String message) {
        this.message = message;
    }


}
