package com.rekrutacja.transport.utils.driver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class DriverException extends RuntimeException {

    protected DriverError driverError;
    protected HttpStatus status;

}
