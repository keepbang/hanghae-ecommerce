package com.hhplus.commerce.app.product.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.app.common.exception.InvalidRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class ProductValidatorTest {

  private ProductValidator productValidator = new ProductValidator();

  @DisplayName("quantityValidation(): 상품 수량은 0보다 큰 양수만 입력 가능합니다.")
  @ParameterizedTest
  @ValueSource(ints = {0, -1})
  void quantityValidation_invalidRequestException(int quantity) {
    // given
    // when
    // then
    assertThatThrownBy(() -> productValidator.quantityValidation(quantity))
        .isInstanceOf(InvalidRequestException.class);
  }

  @DisplayName("priceValidation(): 상품 가격은 0보다 큰 양수만 입력 가능합니다.")
  @ParameterizedTest
  @ValueSource(longs = {0, -1})
  void priceValidation_invalidRequestException(long price) {
    // given
    // when
    // then
    assertThatThrownBy(() -> productValidator.priceValidation(price))
        .isInstanceOf(InvalidRequestException.class);
  }

}