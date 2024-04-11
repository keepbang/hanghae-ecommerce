package com.hhplus.commerce.app.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.app.common.exception.OutOfStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class InventoryTest {

  @Test
  @DisplayName("상품 재고가 부족할 경우 exception 발생")
  public void deduction_outOfStockException() throws Exception {
    //given
    Inventory inventory = new Inventory(1L, 10);
    Integer orderedQuantity = 11;
    //when
    //then
    assertThatThrownBy(() -> inventory.deduction(orderedQuantity))
        .isInstanceOf(OutOfStockException.class);

  }

  @Test
  @DisplayName("상품 재고 차감 성공")
  public void deduction_ok() throws Exception {
    // given
    Inventory inventory = new Inventory(1L, 10);
    Integer orderedQuantity = 10;

    // when
    Inventory actual = inventory.deduction(orderedQuantity);

    // then
    assertThat(actual.getCurrentStock()).isEqualTo(0);
  }

  @Test
  @DisplayName("상품 재고 추가 성공")
  public void addStock_ok() throws Exception {
    //given
    Inventory inventory = new Inventory(1L, 10);
    Integer stock = 10;
    //when
    Inventory actual = inventory.addStock(stock);

    //then
    assertThat(actual.getCurrentStock()).isEqualTo(20);

  }

}