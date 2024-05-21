package com.hhplus.commerce.app.cartitem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.app.cartitem.dto.CartItemRequest;
import com.hhplus.commerce.app.cartitem.dto.CartItemResponse;
import com.hhplus.commerce.app.cartitem.stub.StubCartItemRepository;
import com.hhplus.commerce.app.common.exception.InvalidRequestException;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.service.ProductValidator;
import com.hhplus.commerce.app.product.stub.StubReadProductQuery;
import com.hhplus.commerce.app.user.service.UserQuery;
import com.hhplus.commerce.app.user.service.UserService;
import com.hhplus.commerce.app.user.stub.StubUserRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class CartItemServiceTest {

  private CartItemService cartItemService;
  UUID userKey = UUID.randomUUID();

  private UserQuery userQuery = new UserService(new StubUserRepository(userKey));

  @BeforeEach
  void setUp() {
    List<ProductResponse> productResponses = List.of(new ProductResponse(1L, "바지", 1000L, 10)
        , new ProductResponse(2L, "티셔츠", 1000L, 10)
        , new ProductResponse(3L, "마우스", 23000L, 10)
    );
    cartItemService = new CartItemService(
            userQuery,
        new StubCartItemRepository(),
        new StubReadProductQuery(productResponses),
        new ProductValidator()
    );
  }

  @DisplayName("addToCart(): 상품 수량은 1이상의 숫자만 가능하다.")
  @ParameterizedTest
  @ValueSource(ints = {-1, 0})
  void addToCart_invalidRequestException(Integer quantity) {
    // given
    CartItemRequest request = new CartItemRequest(
        userKey,
        1L,
        quantity
    );
    // when
    // then
    assertThatThrownBy(() -> cartItemService.addToCart(request))
        .isInstanceOf(InvalidRequestException.class);
  }

  @DisplayName("addToCart(): 사용자가 요청한 수량으로 장바구니에 추가된다.")
  @Test
  void addToCart_ok() {
    // given
    int quantity = 1;
    CartItemRequest request = new CartItemRequest(
        userKey,
        1L,
        quantity
    );


    // when
    cartItemService.addToCart(request);
    // then
    List<CartItemResponse> responses = cartItemService.getCartItems(userKey);
    assertThat(responses.get(0).quantity()).isEqualTo(quantity);
  }

  @DisplayName("removeCartItem(): 장바구니 삭제")
  @Test
  void removeCartItem_ok() {
    // given
    Long productId = 1L;

    CartItemRequest request = new CartItemRequest(
        userKey,
        1L,
        1
    );
    cartItemService.addToCart(request);
    // when
    cartItemService.removeCartItem(userKey, productId);

    // then
    List<CartItemResponse> cartItems = cartItemService.getCartItems(userKey);
    assertThat(cartItems).isEmpty();
  }

  @DisplayName("getCartItems(): 장바구니 상품 조회")
  @Test
  void getCartItems_ok() {
    // given
    CartItemRequest request = new CartItemRequest(
        userKey,
        1L,
        4
    );
    cartItemService.addToCart(request);
    // when
    List<CartItemResponse> cartItems = cartItemService.getCartItems(userKey);

    // then
    assertThat(cartItems).hasSize(1);
    assertThat(cartItems.get(0).quantity()).isEqualTo(4);
    assertThat(cartItems.get(0).price()).isEqualTo(1000 * 4);
  }




}