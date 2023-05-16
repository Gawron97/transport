package com.rekrutacja.transport.utils.generalExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class RecordWithThisKeyAlreadyExistsException extends RuntimeException {

    private GeneralError generalError;
    private HttpStatus status;

}
