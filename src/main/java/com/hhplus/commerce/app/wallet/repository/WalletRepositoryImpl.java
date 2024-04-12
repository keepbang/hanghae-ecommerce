package com.hhplus.commerce.app.wallet.repository;

import com.hhplus.commerce.app.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class WalletRepositoryImpl implements WalletRepository {

  private final WalletJpaRepository walletJpaRepository;

  @Override
  public Wallet findByUserId(Long userId) {
    return walletJpaRepository.findByUserId(userId)
        .orElse(new Wallet(userId, 0L));
  }

  @Override
  public Wallet merge(Wallet wallet) {
    return walletJpaRepository.saveAndFlush(wallet);
  }
}
