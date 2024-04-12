package com.hhplus.commerce.app.wallet.repository;

import com.hhplus.commerce.app.wallet.domain.WalletHistory;
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
public class WalletHistoryRepositoryImpl implements WalletHistoryRepository {

  private final WalletHistoryJpaRepository walletHistoryJpaRepository;


  @Override
  public WalletHistory save(WalletHistory walletHistory) {
    return walletHistoryJpaRepository.save(walletHistory);
  }
}
