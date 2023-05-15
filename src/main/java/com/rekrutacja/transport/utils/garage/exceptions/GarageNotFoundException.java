package com.rekrutacja.transport.utils.garage.exceptions;

import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GarageNotFoundException extends RuntimeException {

    private GarageError garageError;
    private HttpStatus status;

}
