package com.rekrutacja.transport.utils.driver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class DriverNotFoundException extends RuntimeException {

    private DriverError driverError;
    private HttpStatus status;

}
