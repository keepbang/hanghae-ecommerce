package com.hhplus.commerce.app.product.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.app.product.domain.Inventory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class InventoryRepositoryImplTest {

  @Mock
  private InventoryJpaRepository inventoryJpaRepository;

  @InjectMocks
  private InventoryRepositoryImpl inventoryRepository;


  @Test
  @DisplayName("상품 재고가 없을 경우 재고를 0으로 반환")
  public void findById_dataNotFound_stockIsZero() throws Exception {
    //given
    given(inventoryJpaRepository.findById(anyLong()))
        .willReturn(Optional.empty());

    //when
    Inventory inventory = inventoryRepository.findById(1L);

    //then
    assertThat(inventory.getCurrentStock()).isEqualTo(0);

  }

}