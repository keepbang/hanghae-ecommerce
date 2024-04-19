package com.hhplus.commerce.app.cartitem.domain;

import com.hhplus.commerce.app.common.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
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
public class CartItem extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seqId;

  // UUID로 사용자를 조회 후 Long type id로 장바구니 조회
  @Column(name = "user_id")
  private Long userSeqId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "quantity")
  private Integer quantity = 0;

  public CartItem(Long userSeqId, Long productId, Integer quantity) {
    this.userSeqId = userSeqId;
    this.productId = productId;
    this.quantity = quantity;
  }
}
