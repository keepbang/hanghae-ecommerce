package com.hhplus.commerce.app.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.init.IntegrationTest;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.service.InventoryService;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

/**
 * create on 4/30/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class InventoryIntegrationTest extends IntegrationTest {

  @Autowired
  private InventoryService inventoryService;

  @Sql("classpath:db/tableInit.sql")
  @DisplayName("재고 차감시 재고가 부족하면 실패를 해야한다.")
  @Test
  void inventory_concurrency_test() throws Exception {
    // given
    long productId = 1L;
    int numberOfThreads = 1100;
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    // when - 주문
    ConcurrentLinkedQueue<Integer> failedOrder = new ConcurrentLinkedQueue<>();
    for (int i = 0; i < numberOfThreads; i++) {
      int finalI = i;
      executorService.execute(() -> {
        try {
          OrderItemRequest request = new OrderItemRequest(
              1L,
              1L,
              1
          );

          inventoryService.orderItemDeduction(request.productId().toString(),
              request);

        } catch (Exception e) {
          failedOrder.add(finalI);
        }
        latch.countDown();
      });
    }

    latch.await();
    // then
    ProductResponse productResponse = getProductResponse(productId);
    assertThat(productResponse.stock()).isZero();
    assertThat(failedOrder).hasSize(100);
  }

}
