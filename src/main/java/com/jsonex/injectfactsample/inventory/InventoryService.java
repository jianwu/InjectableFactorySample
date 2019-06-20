package com.jsonex.injectfactsample.inventory;

import com.jsonex.core.factory.InjectableInstance;
import com.jsonex.injectfactsample.common.LineItem;
import com.jsonex.injectfactsample.order.Address;
import com.jsonex.injectfactsample.order.Order;

public interface InventoryService {
  InjectableInstance<InventoryService> instance = InjectableInstance.of(InventoryServiceImpl::new);
  static InventoryService get() { return instance.get(); }

  boolean checkInventory(LineItem lineItem);
  void updateInventory(LineItem lineItem);
}
