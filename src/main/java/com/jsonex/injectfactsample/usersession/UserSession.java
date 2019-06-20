package com.jsonex.injectfactsample.usersession;

import com.jsonex.core.factory.InjectableFactory;

public interface UserSession {
  InjectableFactory<Void, UserSession> factory =
      InjectableFactory.of((v) -> new UserSessionImpl(), InjectableFactory.CachePolicy.THREAD_LOCAL);
  static UserSession get() { return factory.get(); }

  String getLoginUserId();
}
