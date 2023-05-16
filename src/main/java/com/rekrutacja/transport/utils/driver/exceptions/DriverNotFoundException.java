package com.rekrutacja.transport.utils.driver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DriverNotFoundException extends DriverException {

    public DriverNotFoundException(DriverError driverError, HttpStatus status) {
        super(driverError, status);
    }

}
