package com.rekrutacja.transport.utils.driver.exceptions;

import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public class DriverNeedGarageException extends DriverException {

    public DriverNeedGarageException(DriverError driverError, HttpStatus status) {
        super(driverError, status);
    }

}
