package com.rekrutacja.transport.utils.garage.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class GarageException extends RuntimeException {

    protected GarageError garageError;
    protected HttpStatus status;

}
