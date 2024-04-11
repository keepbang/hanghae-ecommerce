package com.hhplus.commerce.app.common.exception;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public enum ErrorMessage {

  NOT_FOUND_MESSAGE("데이터를 찾을 수 없습니다."),
  OUT_OF_STOCK_MESSAGE("상품 재고가 부족합니다.");

  private String message;

  ErrorMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
