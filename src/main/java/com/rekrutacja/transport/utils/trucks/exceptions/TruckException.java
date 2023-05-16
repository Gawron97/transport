package com.rekrutacja.transport.utils.trucks.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class TruckException extends RuntimeException {

    protected TruckError truckError;
    protected HttpStatus status;

}
