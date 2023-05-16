package com.rekrutacja.transport.utils.trucks.exceptions;

import com.rekrutacja.transport.utils.garage.exceptions.GarageException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class TruckNeedGarageException extends TruckException {

    public TruckNeedGarageException(TruckError truckError, HttpStatus status) {
        super(truckError, status);
    }

}
