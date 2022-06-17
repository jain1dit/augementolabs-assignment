package com.augmentolabs.rmzcorp.realestate.exceptions;

import lombok.AllArgsConstructor;

import java.util.Date;

@lombok.Data
@AllArgsConstructor
public class ExceptionEntity {
  private Date date;
  private String message;
  private String details;

  @Override
  public String toString() {
    return "ExceptionEntity{"
        + "date="
        + date
        + ", message='"
        + message
        + '\''
        + ", details='"
        + details
        + '\''
        + '}';
  }
}
