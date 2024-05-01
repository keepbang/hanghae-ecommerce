package com.hhplus.commerce.app.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.common.type.PaymentType;
import com.hhplus.commerce.app.common.type.RecommendType;
import com.hhplus.commerce.app.init.IntegrationTest;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.service.OrderService;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class OrderIntegrationTest extends IntegrationTest {

  // 주문
  @Autowired
  private OrderService orderService;

  @Sql("classpath:db/tableInit.sql")
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
            new OrderItemRequest(3L, 1L, 1),
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
            new OrderItemRequest(3L, 1L, 1),
            new OrderItemRequest(2L, 1L, 1)
        )
    ));

    orderService.order(new OrderRequest(
        this.getUserKey(),
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(3L, 1L, 1)
        )
    ));

    // when
    List<RecommendProductResponse> responseList = orderService.getRecommendProduct(
        RecommendType.RECOMMEND_01);

    // then
    assertThat(responseList).hasSize(3);
    assertThat(responseList.stream()
        .map(RecommendProductResponse::name)
        .toList()).containsExactly("티셔츠","바지","머그컵");

  }

  @Sql("classpath:db/tableInit.sql")
  @DisplayName("동시 주문 시 재고가 부족하면 실패를 해야한다.")
  @Test
  void order_concurrency_test() throws Exception {
    // given
    long productId = 2L;
    int numberOfThreads = 11;
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    // when - 주문
    ConcurrentLinkedQueue<Integer> failedOrder = new ConcurrentLinkedQueue<>();
    for (int i = 0; i < numberOfThreads; i++) {
      int finalI = i;
      executorService.execute(() -> {
        try {
          orderService.order(new OrderRequest(
              this.getUserKey(),
              PaymentType.WALLET,
              "서울 강남구",
              LocalDateTime.now(),
              List.of(new OrderItemRequest(productId, 1L, 1))
          ));
        } catch (Exception e) {
          failedOrder.add(finalI);
        }
        latch.countDown();
      });
    }

    latch.await();
    // then
    assertThat(failedOrder).hasSize(1);
    ProductResponse productResponse = getProductResponse(productId);
    assertThat(productResponse.stock()).isZero();
  }

}
