package com.beaconfire.HRServer.exception.handler;

import com.beaconfire.HRServer.exception.EmploymentStartDateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.beaconfire.HRServer.domain.ErrorMessage;

@ControllerAdvice
public class EmploymentStartDateNotFoundHandler {

    @ExceptionHandler(value = {EmploymentStartDateNotFoundException.class})
    public ResponseEntity<ErrorMessage> handlerEmploymentNotFound(EmploymentStartDateNotFoundException e){
        return new ResponseEntity<ErrorMessage>(ErrorMessage.builder().errorMessage(e.getMessage()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorMessage> handlerException(Exception e){
        return new ResponseEntity(ErrorMessage.builder().errorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
