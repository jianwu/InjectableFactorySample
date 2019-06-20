package com.jsonex.injectfactsample.shoppingcart;

public class ShoppingCartServiceImpl implements ShoppingCartService {

  public ShoppingCart getCartByUser(String user) {
    // Return a dummy object just for demo purpose, actual implementation usually call a remote service
    return new ShoppingCart();
  }
}
