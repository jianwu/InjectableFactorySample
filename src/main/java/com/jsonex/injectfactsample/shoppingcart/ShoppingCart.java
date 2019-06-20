package com.jsonex.injectfactsample.shoppingcart;

import com.jsonex.injectfactsample.common.LineItem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data @Accessors(chain = true)
public class ShoppingCart {
  List<LineItem> lineItems = new ArrayList<>();

  public boolean isEmpty() { return lineItems.isEmpty(); }
}
