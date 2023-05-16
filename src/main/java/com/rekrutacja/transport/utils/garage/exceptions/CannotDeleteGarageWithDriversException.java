package com.rekrutacja.transport.utils.garage.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CannotDeleteGarageWithDriversException extends GarageException {

    public CannotDeleteGarageWithDriversException(GarageError garageError, HttpStatus status) {
        super(garageError, status);
    }

}
