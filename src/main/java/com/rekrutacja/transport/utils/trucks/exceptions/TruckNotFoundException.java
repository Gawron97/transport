package com.rekrutacja.transport.utils.trucks.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TruckNotFoundException extends RuntimeException {

    private TruckError truckError;
    private HttpStatus status;

}
