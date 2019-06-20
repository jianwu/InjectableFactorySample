package com.jsonex.injectfactsample.usersession;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class UserSessionMock implements UserSession {
  @Getter @Setter String loginUserId;
}
