package com.hhplus.commerce.app.cartitem.repository;

import com.hhplus.commerce.app.cartitem.domain.CartItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

  private final CartItemJpaRepository cartItemJpaRepository;

  @Override
  public CartItem save(CartItem cartItem) {
    return cartItemJpaRepository.save(cartItem);
  }

  @Override
  public void deleteByUserIdAndProductId(Long userId, Long productId) {
    cartItemJpaRepository.deleteByUserSeqIdAndProductId(userId, productId);
  }

  @Override
  public List<CartItem> findAllByUserId(Long userId) {
    return cartItemJpaRepository.findAllByUserSeqId(userId);
  }
}
