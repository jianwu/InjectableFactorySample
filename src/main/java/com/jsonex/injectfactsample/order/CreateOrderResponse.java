package com.jsonex.injectfactsample.order;

import com.jsonex.injectfactsample.common.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

@Data @Accessors(chain = true)
public class CreateOrderResponse extends BaseResponse {
  Order order;
}
