package com.hhplus.commerce.app.order.repository;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.QOrderItem;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.product.domain.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RecommendProductRepository extends QuerydslRepositorySupport {

  private final EntityManager em;
  private final JPAQueryFactory queryFactory;

  public RecommendProductRepository(EntityManager em) {
    super(OrderItem.class);
    this.em = em;
    this.queryFactory = new JPAQueryFactory(em);
  }


  public List<RecommendProductResponse> getRecommendProduct(OrderStatus status,
      LocalDateTime startAt, LocalDateTime endAt) {
    QOrderItem orderItem = QOrderItem.orderItem;
    QProduct product = QProduct.product;
    return queryFactory.select(
        Projections.constructor(RecommendProductResponse.class,
            orderItem.orderItemId.productId,
            product.name,
            orderItem.orderItemId.productId.count()
            ))
        .from(orderItem)
          .innerJoin(product)
          .on(orderItem.orderItemId.productId.eq(product.productId))
        .where(orderItem.orderStatus.eq(status)
            .and(orderItem.orderAt.between(startAt, endAt)))
        .groupBy(orderItem.orderItemId.productId)
        .orderBy(orderItem.orderItemId.productId.count().desc())
        .limit(5)
        .fetch();
  }
}
