package com.jsonex.injectfactsample.usersession;

public class UserSessionImpl implements UserSession {
  public String getLoginUserId() {
    return "dummyUser";  // e.g. Could get from HttpServletRequest
  }
}
