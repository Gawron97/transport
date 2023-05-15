package com.rekrutacja.transport.utils.generalExceptionHandling;

import com.rekrutacja.transport.utils.generalExceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GeneralAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorInfo> handleconversionFailedException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(400).body(new ErrorInfo(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleconversionFailedException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body(new ErrorInfo(ex.getMessage()));
    }

}
