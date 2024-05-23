package com.hhplus.commerce.app.order.domain;

import com.hhplus.commerce.app.common.type.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item"
    , indexes = @Index(
        name = "idx_status_order_at",
        columnList = "order_status,order_at"
    )
)
public class OrderItem {

  @EmbeddedId
  private OrderItemId orderItemId;

  @Column
  private Long price;

  @Column
  private Integer quantity;

  @Column
  @Enumerated(value = EnumType.STRING)
  private OrderStatus orderStatus;

  private LocalDateTime orderAt;

  public OrderItem(OrderItemId orderItemId, Long price, Integer quantity, OrderStatus orderStatus,
      LocalDateTime orderAt) {
    this.orderItemId = orderItemId;
    this.price = price;
    this.quantity = quantity;
    this.orderStatus = orderStatus;
    this.orderAt = orderAt;
  }

}
