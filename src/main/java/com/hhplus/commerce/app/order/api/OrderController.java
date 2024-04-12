package com.hhplus.commerce.app.order.api;

import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.common.type.RecommendType;
import com.hhplus.commerce.app.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 4/2/24. create by IntelliJ IDEA.
 *
 * <p> 주문 관련 API </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  /**
   * 상품 주문.
   *
   * @param request 주문 요청.
   */
  @PostMapping
  public ResponseEntity<Void> orderItems(
      @RequestBody OrderRequest request
  ) {
    orderService.order(request);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

  /**
   * 상위 상품 조회 API. 최근 3일간 가장 많이 팔린 상위 5개 상품 정보 조회. 타입에 따라 조회.
   * todo : 통계 db를 구축해서 따로 조회를 해도 될 것 같음.
   */
  @GetMapping("/recommend/{type}")
  public ResponseEntity<List<RecommendProductResponse>> getRecommendOrderByType(
      @PathVariable("type") RecommendType type
  ) {
    return new ResponseEntity<>(
        List.of(
            new RecommendProductResponse(1, 3L, "맥북 프로 16인치", 20L),
            new RecommendProductResponse(2, 4L, "가습기", 15L),
            new RecommendProductResponse(3, 1L, "마우스", 12L),
            new RecommendProductResponse(4, 2L, "키보드", 10L),
            new RecommendProductResponse(5, 7L, "스타벅스텀블러", 5L)
        ),
        HttpStatus.OK
    );
  }

}
