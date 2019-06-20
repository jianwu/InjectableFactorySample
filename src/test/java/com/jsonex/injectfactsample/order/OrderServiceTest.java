package com.jsonex.injectfactsample.order;

import com.jsonex.core.factory.TimeProvider;
import com.jsonex.injectfactsample.common.Error;
import com.jsonex.injectfactsample.common.LineItem;
import com.jsonex.injectfactsample.inventory.InventoryService;
import com.jsonex.injectfactsample.inventory.InventoryServiceImpl;
import com.jsonex.injectfactsample.shoppingcart.ShoppingCart;
import com.jsonex.injectfactsample.shoppingcart.ShoppingCartService;
import com.jsonex.injectfactsample.shoppingcart.ShoppingCartServiceImpl;
import com.jsonex.injectfactsample.usersession.UserSession;
import com.jsonex.injectfactsample.usersession.UserSessionMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {
  private final static String USER_ID = "testUser";
  private final static String ITEM_ID = "itemId";
  private final static List<LineItem> lineItems = new ArrayList<>(Arrays.asList(new LineItem().setItemId(ITEM_ID).setQuantity(2)));
  private boolean hasInventory = true;
  private final static TimeProvider.Mock timeProviderMock = new TimeProvider.Mock().setTimeMillis(12345L);

  @Before public void before() {
    UserSession.factory.setInstance(new UserSessionMock().setLoginUserId(USER_ID));

    ShoppingCartService.instance.setInstance(new ShoppingCartServiceImpl() {  // partial mock
      @Override public ShoppingCart getCartByUser(String user) { return new ShoppingCart().setLineItems(lineItems); }
    });

    InventoryService.instance.setInstance(new InventoryServiceImpl() {
      @Override public boolean checkInventory(LineItem lineItem) { return hasInventory; }
    });

    TimeProvider.instance.setInstance(timeProviderMock);
  }

  @Test public void createOrder_shouldReturnErrorWhenCartIsEmpty() {
    lineItems.clear();

    CreateOrderResponse resp = OrderService.get().createOrder(new CreateOrderRequest().setShippingAddress(new Address()));
    assertEquals(Error.CART_IS_EMPTY, resp.getError());
  }

  @Test public void createOrder_shouldReturnErrorWhenOutOfStock() {
    hasInventory = false;

    CreateOrderResponse resp = OrderService.get().createOrder(new CreateOrderRequest().setShippingAddress(new Address()));
    assertEquals(Error.OUT_OF_STOCK, resp.getError());
  }

  @Test public void createOrder_success() {
    CreateOrderResponse resp = OrderService.get().createOrder(new CreateOrderRequest().setShippingAddress(new Address()));
    assertNull(resp.getError());
    assertEquals(USER_ID, resp.getOrder().getUserId());
    assertEquals(ITEM_ID, resp.getOrder().getLineItems().get(0).getItemId());
    assertEquals(timeProviderMock.getTimeMillis(), resp.getOrder().getCreated().getTime());
  }

  @Test public void testOrderExpired() {
    CreateOrderResponse resp = OrderService.get().createOrder(new CreateOrderRequest().setShippingAddress(new Address()));
    timeProviderMock.add(3600 * 1000);  // 1 hour passed
    assertFalse(OrderService.get().isOrderExpired(resp.getOrder()));
    timeProviderMock.add(48 * 3600 * 1000);  // 2 days passed
    assertTrue(OrderService.get().isOrderExpired(resp.getOrder()));
  }

}
