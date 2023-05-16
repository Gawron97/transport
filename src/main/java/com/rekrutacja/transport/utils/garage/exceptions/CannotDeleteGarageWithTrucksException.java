package com.rekrutacja.transport.utils.garage.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CannotDeleteGarageWithTrucksException extends RuntimeException {

    private GarageError garageError;
    private HttpStatus status;

}
