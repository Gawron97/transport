package com.rekrutacja.transport.utils.driver.exceptions;

import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class DriverNeedGarageException extends RuntimeException{

    private DriverError driverError;
    private HttpStatus status;

}
