package com.hhplus.commerce.app.wallet.service;

import com.hhplus.commerce.app.common.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public class WalletValidator {

  private static final Long LIMIT_NUMBER = 0L;

  public void amountValidation(Long amount) {
    if (amount <= LIMIT_NUMBER) {
      throw new InvalidRequestException("0보다 큰 양수만 입력 가능합니다.");
    }
  }

}
