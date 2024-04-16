package com.hhplus.commerce.app.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.commerce.app.common.exception.OutOfStockException;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import com.hhplus.commerce.app.product.stub.StubInventoryRepository;
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
class InventoryServiceTest {

  private InventoryService inventoryService;

  private InventoryRepository inventoryRepository = new StubInventoryRepository();

  @BeforeEach
  void setUp() throws Exception {

    this.inventoryService = new InventoryService(
        inventoryRepository
    );

    inventoryRepository.save(new Inventory(
        1L, 1
    ));
  }

  @Test
  @DisplayName("orderItemDeduction(): 재고가 부족하면 exception이 발생한다.")
  void orderItemDeduction_outOfStockException() {
    // given
    int quantity = 2;
    OrderItemRequest request = new OrderItemRequest(
        1L, 1L, quantity
    );
    // when
    // then
    assertThatThrownBy(() -> inventoryService.orderItemDeduction(request))
        .isInstanceOf(OutOfStockException.class);
  }

  @Test
  @DisplayName("orderItemDeduction(): 주문한 아이템의 재고를 차감한다.")
  void orderItemDeduction_ok() {
    // given
    int quantity = 1;
    long productId = 1L;
    OrderItemRequest request = new OrderItemRequest(
        productId, 1L, quantity
    );
    // when
    inventoryService.orderItemDeduction(request);
    // then
    Inventory inventory = inventoryRepository.findById(productId);
    assertThat(inventory.getCurrentStock()).isEqualTo(0);
  }


}