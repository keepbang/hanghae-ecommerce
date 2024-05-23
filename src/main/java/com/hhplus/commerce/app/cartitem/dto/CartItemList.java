package com.hhplus.commerce.app.cartitem.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemList {
  private List<CartItemResponse> cartItems;

  public CartItemList(List<CartItemResponse> cartItems) {
    this.cartItems = cartItems;
  }
}
