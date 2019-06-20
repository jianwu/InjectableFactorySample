package com.jsonex.injectfactsample.pricing;

import com.jsonex.core.factory.InjectableInstance;
import com.jsonex.injectfactsample.order.Address;
import com.jsonex.injectfactsample.order.Order;

public interface PricingService {
  InjectableInstance<PricingService> instance = InjectableInstance.of(PricingServiceImpl::new);
  static PricingService get() { return instance.get(); }

  Order calculatePriceAndTax(Order order, Address shippingAddress);
}
