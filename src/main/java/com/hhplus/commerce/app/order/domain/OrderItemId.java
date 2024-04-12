package com.hhplus.commerce.app.order.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
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

  private Long orderSeqId;
  private Long productId;

  protected OrderItemId() {
  }

  public OrderItemId(Long orderSeqId, Long productId) {
    this.orderSeqId = orderSeqId;
    this.productId = productId;
  }
}
