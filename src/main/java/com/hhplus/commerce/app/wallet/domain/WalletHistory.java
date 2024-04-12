package com.hhplus.commerce.app.wallet.domain;

import com.hhplus.commerce.app.common.type.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
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
public class WalletHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long userId;

  @Column
  @Enumerated(value = EnumType.STRING)
  private TransactionType type;

  @Column
  private Long amount;

  @Column
  private LocalDateTime createdAt;

  public WalletHistory(Long userId, TransactionType type, Long amount,
      LocalDateTime createdAt) {
    this.userId = userId;
    this.type = type;
    this.amount = amount;
    this.createdAt = createdAt;
  }
}
