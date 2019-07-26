package com.jsonex.injectfactsample.usersession;

import com.jsonex.core.factory.InjectableFactory;
import com.jsonex.core.factory.InjectableFactory.CacheScope;

public interface UserSession {
  InjectableFactory<Void, UserSession> factory = InjectableFactory.of(v -> new UserSessionImpl(), CacheScope.THREAD_LOCAL);
  static UserSession get() { return factory.get(); }

  String getLoginUserId();
}
