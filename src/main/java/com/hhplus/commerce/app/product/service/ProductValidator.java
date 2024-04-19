package com.hhplus.commerce.app.product.service;

import com.hhplus.commerce.app.common.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public final class ProductValidator {

  private static final int MIN_QUANTITY = 1;
  private static final long MIN_PRICE = 1;

  public void quantityValidation(int quantity) {
    if (quantity < MIN_QUANTITY) {
      throw new InvalidRequestException("상품 수량은 0보다 큰 양수만 가능합니다.");
    }
  }

  public void priceValidation(long price) {
    if (price < MIN_PRICE) {
      throw new InvalidRequestException("상품 가격은 0보다 큰 양수만 가능합니다.");
    }
  }
}
