package com.hhplus.commerce.app.order.stub;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.OrderItemId;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.repository.OrderItemRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
public class StubOrderItemRepository implements OrderItemRepository {

  Map<OrderItemId, OrderItem> orderItemMap = new HashMap<>();

  @Override
  public void saveAll(List<OrderItem> orderItems) {
    orderItems.forEach(orderItem -> {
      orderItemMap.put(orderItem.getOrderItemId(), orderItem);
    });
  }

  @Override
  public List<RecommendProductResponse> getRecommendProduct(OrderStatus status,
      LocalDateTime startAt, LocalDateTime endAt) {
    return List.of();
  }

  public Map<OrderItemId, OrderItem> getOrderItemMap() {
    return orderItemMap;
  }
}
