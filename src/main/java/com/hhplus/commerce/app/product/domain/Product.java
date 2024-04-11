package com.hhplus.commerce.app.product.domain;

import com.hhplus.commerce.app.common.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "product")
public class Product extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private Long price;

  protected Product() {
  }

  public Product(Long productId, String name, Long price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  public Product(String name, Long price) {
    this.name = name;
    this.price = price;
  }

  public Long getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }
}
