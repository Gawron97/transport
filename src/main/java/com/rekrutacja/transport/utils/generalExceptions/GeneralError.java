package com.rekrutacja.transport.utils.generalExceptions;

import lombok.Getter;

@Getter
public enum GeneralError {

    CANNOT_ASSIGN_KEY_TO_ADDING_RECORD_EXCEPTION("Cannot assign id manually to new record");

    private final String message;

    GeneralError(String message) {
        this.message = message;
    }

}
