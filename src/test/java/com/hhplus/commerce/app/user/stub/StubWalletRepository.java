package com.hhplus.commerce.app.user.stub;

import com.hhplus.commerce.app.user.domain.Wallet;
import com.hhplus.commerce.app.user.repository.WalletRepository;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubWalletRepository implements WalletRepository {

  Wallet wallet;

  public StubWalletRepository() {
    this.wallet = new Wallet(
        1L, 0L
    );
  }

  @Override
  public Wallet findByUserId(Long userId) {
    return this.wallet;
  }

  @Override
  public Wallet merge(Wallet wallet) {
    this.wallet = wallet;
    return this.wallet;
  }
}
