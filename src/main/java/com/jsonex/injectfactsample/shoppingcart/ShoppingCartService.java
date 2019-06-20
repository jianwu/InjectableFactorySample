package com.jsonex.injectfactsample.shoppingcart;

import com.jsonex.core.factory.InjectableInstance;

public interface ShoppingCartService {
  InjectableInstance<ShoppingCartService> instance = InjectableInstance.of(ShoppingCartServiceImpl::new);
  static ShoppingCartService get() { return instance.get(); }
  ShoppingCart getCartByUser(String user);
}
