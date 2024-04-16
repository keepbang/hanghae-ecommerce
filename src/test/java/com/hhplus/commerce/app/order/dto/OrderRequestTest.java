package com.hhplus.commerce.app.order.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.commerce.app.common.type.PaymentType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class OrderRequestTest {

  @DisplayName("getTotalPrice(): 주문한 상품들의 총 가격을 구한다.")
  void getTotalPrice_ok() {
    // given
    OrderRequest request = new OrderRequest(
        UUID.randomUUID(),
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 2L, 1),
            new OrderItemRequest(1L, 3L, 1)
        )
    );

    // when
    Long totalPrice = request.getTotalPrice();

    // then
    assertThat(totalPrice).isEqualTo(5L);
  }

}