package com.hhplus.commerce.app.user.domain;

import static com.hhplus.commerce.app.common.exception.ErrorMessage.BALANCE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.app.common.exception.BalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class WalletTest {

  @Test
  @DisplayName("사용자 지갑 충천")
  void walletCharge_ok() throws Exception {
    //given
    Wallet wallet = new Wallet(1L, 1L);
    Long amount = 1L;
    //when
    Wallet charged = wallet.charge(amount);

    //then
    assertThat(charged.getBalance()).isEqualTo(1L + 1L);

  }

  @Test
  @DisplayName("지갑 잔액이 부족할 경우 Exception 발생")
  void walletUse_balanceException() throws Exception {
    // given
    Wallet wallet = new Wallet(1L, 0L);
    Long amount = 1L;
    // when
    // then
    assertThatThrownBy(() -> wallet.use(amount))
        .isInstanceOf(BalanceException.class)
        .hasMessage(BALANCE_ERROR_MESSAGE.getMessage());
  }

}