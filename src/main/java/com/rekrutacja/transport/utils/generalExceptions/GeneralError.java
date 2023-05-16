package com.rekrutacja.transport.utils.generalExceptions;

import lombok.Getter;

@Getter
public enum GeneralError {

    RECORD_WITH_THIS_KEY_ALREADY_EXISTS("Record with following key is already in db");

    private final String message;

    GeneralError(String message) {
        this.message = message;
    }

}
