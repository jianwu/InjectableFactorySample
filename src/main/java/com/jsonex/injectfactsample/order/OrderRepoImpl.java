package com.jsonex.injectfactsample.order;

import com.jsonex.core.type.Operator;
import com.jsonex.injectfactsample.datastore.DataStore;

import java.util.List;

public class OrderRepoImpl implements OrderRepo {
  public final static String DS_NAME = "OrderDS";

  private DataStore getDs() { return DataStore.factory.get(DS_NAME); }

  @Override public Order create(String userId) { return new Order().setUserId(userId); }

  @Override public Order save(Order order) {
    getDs().save(order);
    return order;
  }

  @Override public Order update(Order order) {
    getDs().update(order);
    return order;
  }

  @Override public Order getById(String orderId) { return getDs().getById(orderId, Order.class); }

  @Override public List<Order> getByUserId(String userId) {
    return getDs().find(Operator.eq(Order::getUserId, userId), Order.class);
  }
}
