package com.jsonex.injectfactsample.order;

import com.jsonex.core.factory.InjectableInstance;

import java.util.List;

public interface OrderRepo {
  InjectableInstance<OrderRepo> instance = InjectableInstance.of(OrderRepoImpl::new);
  static OrderRepo get() { return instance.get(); }

  Order create(String userId);
  Order save(Order order);
  Order update(Order order);
  Order getById(String orderId);
  List<Order> getByUserId(String userId);
}
