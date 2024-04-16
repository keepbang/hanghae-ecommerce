package com.hhplus.commerce.app.user.domain;

import com.hhplus.commerce.app.common.entity.AuditEntity;
import com.hhplus.commerce.app.common.exception.BalanceException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Wallet extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long userId;

  @Column
  private Long balance;

  public Wallet(Long userId, Long balance) {
    this.userId = userId;
    this.balance = balance;
  }

  private Wallet(Long id, Long userId, Long balance) {
    this(userId, balance);
    this.id = id;
  }

  public Wallet charge(Long amount) {
    return new Wallet(
        this.id,
        this.userId,
        this.balance + amount
    );
  }

  public Wallet use(Long amount) {
    if (amount > this.balance) {
      throw new BalanceException();
    }

    return new Wallet(
        this.id,
        this.userId,
        this.balance - amount
    );
  }
}
