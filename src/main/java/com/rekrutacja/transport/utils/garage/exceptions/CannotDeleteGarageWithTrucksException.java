package com.rekrutacja.transport.utils.garage.exceptions;


import org.springframework.http.HttpStatus;

public class CannotDeleteGarageWithTrucksException extends GarageException {

    public CannotDeleteGarageWithTrucksException(GarageError garageError, HttpStatus status) {
        super(garageError, status);
    }

}
