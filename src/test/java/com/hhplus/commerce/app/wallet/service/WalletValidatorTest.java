package com.hhplus.commerce.app.wallet.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.commerce.app.common.exception.InvalidRequestException;
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
class WalletValidatorTest {

  private WalletValidator walletValidator = new WalletValidator();

  @Test
  @DisplayName("0이 들어올 경우 exception 발생")
  public void amountValidation_requestToZero() throws Exception {
    //given
    Long value = 0L;
    //when
    //then
    assertThatThrownBy(() -> walletValidator.amountValidation(value))
        .isInstanceOf(InvalidRequestException.class);

  }

  @Test
  @DisplayName("음수가 들어올 경우 exception 발생")
  public void amountValidation_requestToNegative() throws Exception {
    //given
    Long value = -1L;
    //when
    //then
    assertThatThrownBy(() -> walletValidator.amountValidation(value))
        .isInstanceOf(InvalidRequestException.class);

  }

}