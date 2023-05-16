package com.rekrutacja.transport.utils.trucks.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TruckNeedGarageException extends RuntimeException{

    private TruckError truckError;
    private HttpStatus status;

}
