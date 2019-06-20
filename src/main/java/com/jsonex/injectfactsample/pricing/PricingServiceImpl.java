package com.jsonex.injectfactsample.pricing;

import com.jsonex.injectfactsample.order.Address;
import com.jsonex.injectfactsample.order.Order;

public class PricingServiceImpl implements PricingService {

  @Override
  public Order calculatePriceAndTax(Order order, Address shippingAddress) {
    // TODO: actual calculation logic
    return order;
  }
}
