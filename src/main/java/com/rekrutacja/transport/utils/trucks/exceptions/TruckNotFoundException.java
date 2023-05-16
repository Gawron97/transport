package com.rekrutacja.transport.utils.trucks.exceptions;

import org.springframework.http.HttpStatus;


public class TruckNotFoundException extends TruckException {

    public TruckNotFoundException(TruckError truckError, HttpStatus status) {
        super(truckError, status);
    }

}
