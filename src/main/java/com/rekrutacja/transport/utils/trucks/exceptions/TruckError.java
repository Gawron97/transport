package com.rekrutacja.transport.utils.trucks.exceptions;

import com.rekrutacja.transport.model.Truck;
import lombok.Data;
import lombok.Getter;

@Getter
public enum TruckError {

    TRUCK_NOT_FOUND("Truck not found"),
    TRUCK_HAVE_TO_HAS_GARAGE("Truck have to has garage");

    private final String message;

    TruckError(String message) {
        this.message = message;
    }

}
