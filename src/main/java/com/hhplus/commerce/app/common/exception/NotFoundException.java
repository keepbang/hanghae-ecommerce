package com.hhplus.commerce.app.common.exception;

import static com.hhplus.commerce.app.common.exception.ErrorMessage.NOT_FOUND_MESSAGE;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException() {
    super(NOT_FOUND_MESSAGE.getMessage());
  }
}
