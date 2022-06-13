package com.augmentolabs.rmzcorp.realestate.exceptions;


import org.springframework.cglib.core.WeakCacheKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomisedResponseEntity {

    @ExceptionHandler(IdNotFoundException.class)
    public final ResponseEntity<Object> customisedIdNotFoundException(Exception ex, WebRequest webRequest){
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), ex.getMessage(), webRequest.getDescription(false) );
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ZoneNameException.class)
    public final ResponseEntity<Object> customisedZoneNameException(Exception ex, WebRequest webRequest){
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), ex.getMessage(), webRequest.getDescription(false) );
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNameException.class)
    public final ResponseEntity<Object> customisedLocationNameException(Exception ex, WebRequest webRequest){
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), ex.getMessage(), webRequest.getDescription(false) );
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_ACCEPTABLE);
    }

}
