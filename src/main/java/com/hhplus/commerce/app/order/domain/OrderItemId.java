package com.hhplus.commerce.app.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Embeddable
public class OrderItemId implements Serializable {

  @Column(name = "order_id")
  private Long orderSeqId;
  private Long productId;

  protected OrderItemId() {
  }

  public OrderItemId(Long orderSeqId, Long productId) {
    this.orderSeqId = orderSeqId;
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderItemId that = (OrderItemId) o;
    return Objects.equals(orderSeqId, that.orderSeqId)
        && Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderSeqId, productId);
  }
}
