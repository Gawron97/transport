package com.rekrutacja.transport.utils.trucks.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TruckNotFoundException extends RuntimeException {

    private TruckError truckError;
    private HttpStatus status;

}
