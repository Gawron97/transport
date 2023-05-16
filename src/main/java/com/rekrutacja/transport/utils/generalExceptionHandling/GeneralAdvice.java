package com.rekrutacja.transport.utils.generalExceptionHandling;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import com.rekrutacja.transport.utils.generalExceptions.RecordWithThisKeyAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GeneralAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorInfo> handleConversionFailedException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(400).body(new ErrorInfo(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body(new ErrorInfo(ex.getMessage()));
    }

    @ExceptionHandler(RecordWithThisKeyAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> handleRecordWithKeyAlreadyExistsException(RecordWithThisKeyAlreadyExistsException ex) {
        return ResponseEntity.status(400).body(new ErrorInfo(ex.getMessage()));
    }

}
