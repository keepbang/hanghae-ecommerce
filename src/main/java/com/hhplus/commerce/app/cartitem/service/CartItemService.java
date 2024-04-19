package com.hhplus.commerce.app.cartitem.service;

import com.hhplus.commerce.app.cartitem.domain.CartItem;
import com.hhplus.commerce.app.cartitem.dto.CartItemRequest;
import com.hhplus.commerce.app.cartitem.dto.CartItemResponse;
import com.hhplus.commerce.app.cartitem.repository.CartItemRepository;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.service.ProductValidator;
import com.hhplus.commerce.app.product.service.ReadProductQuery;
import com.hhplus.commerce.app.user.service.ReadUserQuery;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemService {

  private final ReadUserQuery readUserQuery;

  private final CartItemRepository cartItemRepository;

  private final ReadProductQuery readProductQuery;

  private final ProductValidator productValidator;

  public void addToCart(CartItemRequest request) {
    productValidator.quantityValidation(request.quantity());

    Long userId = readUserQuery.getUserIdByUserKey(request.userKey());
    CartItem cartItem = new CartItem(
        userId,
        request.productId(),
        request.quantity()
    );

    cartItemRepository.save(cartItem);
  }

  public List<CartItemResponse> getCartItems(UUID userKey) {
    Long userId = readUserQuery.getUserIdByUserKey(userKey);
    return cartItemRepository.findAllByUserId(userId)
        .stream()
        .map(entity -> {
          ProductResponse product = readProductQuery.findById(entity.getProductId());

          return new CartItemResponse(
              entity.getProductId(),
              entity.getQuantity(),
              Objects.isNull(entity.getModifiedAt())
                  ? entity.getCreatedAt()
                  : entity.getModifiedAt(),
              product.name(),
              product.stock(),
              product.price() * entity.getQuantity()
          );

        })
        .toList();
  }

  public void removeCartItem(UUID userKey, Long productId) {
    Long userId = readUserQuery.getUserIdByUserKey(userKey);
    cartItemRepository.deleteByUserIdAndProductId(userId, productId);
  }
}
