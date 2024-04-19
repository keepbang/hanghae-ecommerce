package com.hhplus.commerce.app.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.common.type.PaymentType;
import com.hhplus.commerce.app.common.type.RecommendType;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.service.OrderService;
import com.hhplus.commerce.app.product.dto.ProductRequest;
import com.hhplus.commerce.app.product.service.ProductService;
import com.hhplus.commerce.app.user.domain.User;
import com.hhplus.commerce.app.user.dto.ChargeRequest;
import com.hhplus.commerce.app.user.repository.UserJpaRepository;
import com.hhplus.commerce.app.user.service.WalletService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class OrderIntegrationTest {

  // 상품
  @Autowired
  private ProductService productService;

  // 사용자
  @Autowired
  private UserJpaRepository userJpaRepository;
  @Autowired
  private WalletService walletService;

  // 주문
  @Autowired
  private OrderService orderService;

  @DisplayName("최근 3일동안 가장 많이 주문한 상품 5개 조회")
  @Test
  void getRecommendProduct_Recommend1() throws Exception {
    // given
    // 상품/재고 등록
    productService.save(new ProductRequest("장난감", 1L, 10));
    productService.save(new ProductRequest("바지", 1L, 10));
    productService.save(new ProductRequest("티셔츠", 1L, 10));
    productService.save(new ProductRequest("머그컵", 1L, 10));
    productService.save(new ProductRequest("마우스", 1L, 10));
    productService.save(new ProductRequest("키보드", 1L, 10));

    // 사용자 등록, 지갑 충전
    UUID userKey = UUID.randomUUID();

    userJpaRepository.save(new User(userKey, "kim", "서울 강남구"));
    walletService.charge(new ChargeRequest(
        userKey, 10000L
    ));

    // 주문
    orderService.order(new OrderRequest(
        userKey,
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
        userKey,
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 1L, 1),
            new OrderItemRequest(2L, 1L, 1)
        )
    ));

    orderService.order(new OrderRequest(
        userKey,
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
