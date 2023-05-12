package com.rekrutacja.transport.utils.trucks.exceptions;

import com.rekrutacja.transport.model.Truck;
import lombok.Data;
import lombok.Getter;

@Getter
public enum TruckError {

    TRUCK_NOT_FOUND("Truck not found");

    private String message;

    TruckError(String message) {
        this.message = message;
    }

}
