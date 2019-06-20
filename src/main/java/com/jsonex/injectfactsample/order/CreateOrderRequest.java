package com.jsonex.injectfactsample.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data @Accessors(chain = true)
public class CreateOrderRequest {
  Address shippingAddress;
}
