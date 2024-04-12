package com.hhplus.commerce.app.order.repository;

import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import java.util.List;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface OrderItemRepository {

  void saveAll(List<OrderItem> orderItems);

  List<RecommendProductResponse> getRecommendProduct();

}
