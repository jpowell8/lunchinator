package com.ics.lunchinator.webservice.httpMappedExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author joshpowell
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidTimeException extends RuntimeException {
  public InvalidTimeException(String message) {
    super(message);
  }
}
