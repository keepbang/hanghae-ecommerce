package com.hhplus.commerce.app.order.stub;

import com.hhplus.commerce.app.order.domain.Order;
import com.hhplus.commerce.app.order.repository.OrderRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubOrderRepository implements OrderRepository {

  Map<Long, Order> map = new HashMap<>();

  Long curr = 1L;

  @Override
  public Order save(Order order) {
    map.put(curr++, order);
    return order;
  }

  public Order getOrder(Long orderId) {
    return map.get(orderId);
  }
}
