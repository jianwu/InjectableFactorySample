package com.jsonex.injectfactsample.order;

import com.jsonex.core.factory.InjectableInstance;
import com.jsonex.core.factory.TimeProvider;
import com.jsonex.injectfactsample.common.Error;
import com.jsonex.injectfactsample.common.Validator;
import com.jsonex.injectfactsample.common.ValidatorException;
import com.jsonex.injectfactsample.inventory.InventoryService;
import com.jsonex.injectfactsample.pricing.PricingService;
import com.jsonex.injectfactsample.shoppingcart.ShoppingCart;
import com.jsonex.injectfactsample.shoppingcart.ShoppingCartService;
import com.jsonex.injectfactsample.usersession.UserSession;

public class OrderService {
  public final static InjectableInstance<OrderService> instance = InjectableInstance.of(OrderService.class);
  static OrderService get() { return instance.get(); }

  public CreateOrderResponse createOrder(CreateOrderRequest request) {
    CreateOrderResponse resp = new CreateOrderResponse();
    Validator valid = Validator.get();
    try {
      String loginUser = UserSession.get().getLoginUserId();
      ShoppingCart cart = ShoppingCartService.get().getCartByUser(loginUser);
      valid.isTrue(!cart.isEmpty(), Error.CART_IS_EMPTY, "user:" + loginUser);

      cart.getLineItems().forEach(item ->
          valid.isTrue(InventoryService.get().checkInventory(item), Error.OUT_OF_STOCK, "item:" + item.getItemId()));

      Order order = OrderRepo.get().create(loginUser)
          .setCreated(TimeProvider.get().getDate());

      PricingService.get().calculatePriceAndTax(order, request.shippingAddress);

      cart.getLineItems().forEach(item -> InventoryService.get().updateInventory(item));
      order.setLineItems(cart.getLineItems());
      OrderRepo.get().save(order);

      return resp.setOrder(order);
    } catch (ValidatorException e) {
      resp.setError((Error)e.getError()).setMessage(e.getMessage());
      return resp;
    }
  }

  public final static long EXP_TIME = 24 * 3600 * 1000; // 24 hours
  public boolean isOrderExpired(Order order) {
    return TimeProvider.get().getTimeMillis() - order.getCreated().getTime() > EXP_TIME;
  }
}
