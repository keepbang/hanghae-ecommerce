package com.hhplus.commerce.app.common.exception;

import static com.hhplus.commerce.app.common.exception.ErrorMessage.BALANCE_ERROR_MESSAGE;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class BalanceException extends RuntimeException {

  public BalanceException() {
    super(BALANCE_ERROR_MESSAGE.getMessage());
  }
}
