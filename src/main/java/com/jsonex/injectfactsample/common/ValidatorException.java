package com.jsonex.injectfactsample.common;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ValidatorException extends RuntimeException {
  @Getter final Enum error;
  public ValidatorException(Enum<?> error, String message) {
    super(message);
    this.error = error;
  }
}
