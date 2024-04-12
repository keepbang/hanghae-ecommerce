package com.hhplus.commerce.app.order.repository;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.OrderItemId;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, OrderItemId> {

  @Query("""
    select
      oi.orderItemId.productId,
      sum(oi.orderItemId.productId)
    from OrderItem oi
    where
      oi.orderStatus = :orderStatus
      and
      oi.orderAt between :startAt and :endAt
  """)
  List<RecommendProductResponse> getRecommendProduct(
      @Param("orderStatus") OrderStatus orderStatus,
      @Param("startAt") LocalDateTime startAt,
      @Param("endAt") LocalDateTime endAt
  );

}
