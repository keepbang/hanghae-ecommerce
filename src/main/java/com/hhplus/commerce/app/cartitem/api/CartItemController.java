package com.hhplus.commerce.app.cartitem.api;

import com.hhplus.commerce.app.cartitem.dto.CartItemRequest;
import com.hhplus.commerce.app.cartitem.dto.CartItemResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 4/2/24. create by IntelliJ IDEA.
 *
 * <p> 장바구니 관련 API </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/carts")
public class CartItemController {

  /**
   * 장바구니 추가(수량 변경). 기존에 있던거에 엎어치기.
   */
  @PutMapping
  public ResponseEntity<Void> addToCart(
      @RequestBody CartItemRequest request
  ) {
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

  /**
   * 장바구니에서 상품 삭제.
   */
  @DeleteMapping("/users/{userId}/products/{productId}")
  public ResponseEntity<Void> removeCart(
      @PathVariable("userId") Long userId,
      @PathVariable("productId") Long productId
  ) {
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

  /**
   * 장바구니 조회
   */
  @GetMapping("/users/{userId}")
  public ResponseEntity<List<CartItemResponse>> getCartItems(
      @PathVariable("userId") Long userId
  ) {
    return new ResponseEntity<>(
        List.of(
            new CartItemResponse(1L, "맥북", 1, 1_000_000L, LocalDateTime.now()),
            new CartItemResponse(2L, "마우스", 1, 10_000L, LocalDateTime.now())
        ),
        HttpStatus.OK
    );
  }

}
