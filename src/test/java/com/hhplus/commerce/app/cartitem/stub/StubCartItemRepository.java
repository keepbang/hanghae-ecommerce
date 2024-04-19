package com.hhplus.commerce.app.cartitem.stub;

import com.hhplus.commerce.app.cartitem.domain.CartItem;
import com.hhplus.commerce.app.cartitem.repository.CartItemRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubCartItemRepository implements CartItemRepository {

  List<CartItem> cartItems = new ArrayList<>();

  @Override
  public CartItem save(CartItem cartItem) {
    cartItems.add(cartItem);
    return cartItem;
  }

  @Override
  public void deleteByUserIdAndProductId(Long userId, Long productId) {
    cartItems.removeIf(cartItem ->
        cartItem.getProductId().equals(productId));
  }

  @Override
  public List<CartItem> findAllByUserId(Long userId) {
    return cartItems;
  }
}
