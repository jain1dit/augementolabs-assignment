package com.augmentolabs.rmzcorp.realestate.exceptions;

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
  public final ResponseEntity<Object> customisedIdNotFoundException(
      Exception ex, WebRequest webRequest) {
    ExceptionEntity exceptionEntity =
        new ExceptionEntity(new Date(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
  }
}
