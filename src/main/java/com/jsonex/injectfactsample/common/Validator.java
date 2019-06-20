package com.jsonex.injectfactsample.common;

import com.jsonex.core.factory.InjectableInstance;

public class Validator {
  public final static InjectableInstance<Validator> instance = InjectableInstance.of(Validator::new);
  public static Validator get() { return instance.get(); }

  public void isTrue(boolean val, Enum error, String message) {
    if (!val)
      throw new ValidatorException(error, message);
  }
}
