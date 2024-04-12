package com.hhplus.commerce.app.product.domain;

import com.hhplus.commerce.app.common.entity.AuditEntity;
import com.hhplus.commerce.app.common.exception.OutOfStockException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "inventory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory extends AuditEntity {

  @Id
  private Long productId;


  @Column(name = "current_stock")
  private Integer currentStock;


  public Inventory(Long productId, Integer currentStock) {
    this.productId = productId;
    this.currentStock = currentStock;
  }

  // 재고 차감
  public Inventory deduction(Integer quantity) {
    if (this.currentStock < quantity) {
      throw new OutOfStockException();
    }

    return new Inventory(
        this.productId,
        this.currentStock - quantity
    );

  }

  // 재고 증가
  public Inventory addStock(Integer quantity) {
    return new Inventory(
        this.productId,
        this.currentStock + quantity
    );
  }


}
