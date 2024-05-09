package com.hhplus.commerce.app.order.repository;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

  private final OrderItemJpaRepository orderItemJpaRepository;
  private final RecommendProductRepository recommendProductRepository;

  @Override
  public void saveAll(List<OrderItem> orderItems) {
    orderItemJpaRepository.saveAll(orderItems);
  }

  @Override
  public List<RecommendProductResponse> getRecommendProduct(
      OrderStatus status,
      LocalDateTime startAt,
      LocalDateTime endAt
  ) {
    return recommendProductRepository.getRecommendProduct(status, startAt, endAt);
  }
}
