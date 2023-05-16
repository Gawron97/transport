package com.rekrutacja.transport.utils.garage.exceptions;

import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class GarageNotFoundException extends GarageException {

    public GarageNotFoundException(GarageError garageError, HttpStatus status) {
        super(garageError, status);
    }

}
