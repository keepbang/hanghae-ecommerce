package com.hhplus.commerce.app.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.common.type.TransactionType;
import com.hhplus.commerce.app.user.domain.WalletHistory;
import com.hhplus.commerce.app.user.dto.ChargeRequest;
import com.hhplus.commerce.app.user.dto.UseRequest;
import com.hhplus.commerce.app.user.dto.WalletResponse;
import com.hhplus.commerce.app.user.repository.UserRepository;
import com.hhplus.commerce.app.user.repository.WalletHistoryRepository;
import com.hhplus.commerce.app.user.repository.WalletRepository;
import com.hhplus.commerce.app.user.stub.StubUserRepository;
import com.hhplus.commerce.app.user.stub.StubWalletHistoryRepository;
import com.hhplus.commerce.app.user.stub.StubWalletRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class WalletServiceTest {

  UUID userKey = UUID.randomUUID();

  private WalletService walletService;
  private UserRepository userRepository = new StubUserRepository(userKey);
  private WalletRepository walletRepository = new StubWalletRepository();
  private WalletHistoryRepository walletHistoryRepository = new StubWalletHistoryRepository();


  @BeforeEach
  void setUp() {
    this.walletService = new WalletService(
        userRepository,
        walletRepository,
        walletHistoryRepository
    );
  }

  @Test
  @DisplayName("getUserWallet(): 사용자 지갑을 조회한다.")
  void getUserWallet() throws Exception {
    //given
    //when
    WalletResponse response = walletService.getUserWallet(userKey);

    //then
    assertThat(response.balance()).isEqualTo(0L);
    assertThat(response.userKey()).isEqualTo(userKey);

  }

  @DisplayName("charge(): 사용자가 입력한 금액이 충전된다.")
  @Test
  void charge() throws Exception {
    // given
    Long chargeAmount = 1L;

    ChargeRequest request = new ChargeRequest(
        userKey,
        chargeAmount
    );

    // when
    walletService.charge(request);

    // then
    List<WalletHistory> walletHistories = walletHistoryRepository.findByUserId(1L);
    WalletResponse userWallet = walletService.getUserWallet(userKey);

    assertThat(userWallet.balance()).isEqualTo(chargeAmount);
    assertThat(walletHistories.size()).isEqualTo(1);
    assertThat(walletHistories.get(0).getType()).isEqualTo(TransactionType.CHARGE);
  }

  @DisplayName("use(): 사용자가 입력한 금액이 사용된다.")
  @Test
  void use() throws Exception {
    // given
    Long chargeAmount = 1L;
    Long useAmount = 1L;

    walletService.charge(new ChargeRequest(
        userKey,
        chargeAmount
    ));

    UseRequest useRequest = new UseRequest(
        userKey,
        useAmount
    );

    // when
    String transactionId = walletService.use(useRequest);

    // then
    List<WalletHistory> walletHistories = walletHistoryRepository.findByUserId(1L);
    WalletResponse userWallet = walletService.getUserWallet(userKey);

    assertThat(transactionId).isNotNull();
    assertThat(userWallet.balance()).isEqualTo(0L);
    assertThat(walletHistories.size()).isEqualTo(2);
    assertThat(
        walletHistories.stream()
            .map(WalletHistory::getType)
            .toList()
        ).containsExactly(TransactionType.CHARGE, TransactionType.USE);
  }

}