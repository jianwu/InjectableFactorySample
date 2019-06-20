package com.jsonex.injectfactsample.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data @Accessors(chain = true)
public class LineItem {
  String itemId;
  int quantity;
}
