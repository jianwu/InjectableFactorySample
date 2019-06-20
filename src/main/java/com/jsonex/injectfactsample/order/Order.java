package com.jsonex.injectfactsample.order;

import com.jsonex.core.type.Identifiable;
import com.jsonex.injectfactsample.common.LineItem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data @Accessors(chain = true)
public class Order implements Identifiable<String> {
  String id;
  String userId;
  List<LineItem> lineItems;
  Date created;
}
