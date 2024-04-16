package com.hhplus.commerce.app.user.stub;

import com.hhplus.commerce.app.user.domain.WalletHistory;
import com.hhplus.commerce.app.user.repository.WalletHistoryRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubWalletHistoryRepository implements WalletHistoryRepository {

  List<WalletHistory> walletHistoryList = new ArrayList<>();

  @Override
  public WalletHistory save(WalletHistory walletHistory) {
    walletHistoryList.add(walletHistory);
    return walletHistory;
  }

  @Override
  public List<WalletHistory> findByUserId(Long userId) {
    return this.walletHistoryList;
  }

}
