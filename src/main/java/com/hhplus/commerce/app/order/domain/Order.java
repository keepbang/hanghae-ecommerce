package com.hhplus.commerce.app.order.domain;

import com.hhplus.commerce.app.common.type.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
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
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seq_id")
  private Long seqId;

  @Column
  private UUID orderId;

  @Column
  private UUID userId;

  @Column
  private Long totalPrice;

  @Column
  private Integer productQuantity;

  @Column
  private String address;

  @Column(length = 20)
  @Enumerated(value = EnumType.STRING)
  private PaymentType paymentType;

  @Column
  private String transactionId;

  @Column
  private LocalDateTime orderAt;

  public Order(UUID orderId, UUID userId, Long totalPrice, Integer productQuantity, String address,
      PaymentType paymentType, String transactionId, LocalDateTime orderAt) {
    this.orderId = orderId;
    this.userId = userId;
    this.totalPrice = totalPrice;
    this.productQuantity = productQuantity;
    this.address = address;
    this.paymentType = paymentType;
    this.transactionId = transactionId;
    this.orderAt = orderAt;
  }
}
