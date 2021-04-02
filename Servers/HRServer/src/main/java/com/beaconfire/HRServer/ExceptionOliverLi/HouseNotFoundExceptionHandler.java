package com.beaconfire.HRServer.ExceptionOliverLi;

import com.beaconfire.HRServer.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HouseNotFoundExceptionHandler {

    @ExceptionHandler(value = {HouseNotFoundException.class})
    public ResponseEntity<ErrorMessage> houseNotFoundHandler(HouseNotFoundException e){
        return new ResponseEntity(ErrorMessage.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
