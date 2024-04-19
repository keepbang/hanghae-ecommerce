package com.hhplus.commerce.app.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.common.type.PaymentType;
import com.hhplus.commerce.app.common.type.RecommendType;
import com.hhplus.commerce.app.init.IntegrationTest;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.repository.OrderItemJpaRepository;
import com.hhplus.commerce.app.order.repository.OrderJpaRepository;
import com.hhplus.commerce.app.order.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class OrderIntegrationTest extends IntegrationTest {

  @Autowired
  private OrderJpaRepository orderJpaRepository;
  @Autowired
  private OrderItemJpaRepository orderItemJpaRepository;

  // 주문
  @Autowired
  private OrderService orderService;

  @BeforeEach
  void setUp() throws Exception {
    orderJpaRepository.deleteAll();
    orderItemJpaRepository.deleteAll();
  }

  @DisplayName("최근 3일동안 가장 많이 주문한 상품 5개 조회")
  @Test
  void getRecommendProduct_Recommend1() throws Exception {
    // given
    // 주문
    orderService.order(new OrderRequest(
        this.getUserKey(),
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 1L, 1),
            new OrderItemRequest(2L, 1L, 1),
            new OrderItemRequest(4L, 1L, 1)
        )
    ));

    orderService.order(new OrderRequest(
        this.getUserKey(),
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 1L, 1),
            new OrderItemRequest(2L, 1L, 1)
        )
    ));

    orderService.order(new OrderRequest(
        this.getUserKey(),
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 1L, 1)
        )
    ));

    // when
    List<RecommendProductResponse> responseList = orderService.getRecommendProduct(
        RecommendType.RECOMMEND_01);

    // then
    assertThat(responseList).hasSize(3);
    assertThat(responseList.stream()
        .map(RecommendProductResponse::name)
        .toList()).containsExactly("장난감","바지","머그컵");

  }

}
