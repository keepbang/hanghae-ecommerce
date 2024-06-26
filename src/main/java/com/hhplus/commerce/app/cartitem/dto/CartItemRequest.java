package com.hhplus.commerce.app.cartitem.dto;

import java.util.UUID;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record CartItemRequest(
    UUID userKey, // 사용자 아이디.
    Long productId, // 상품 아이디.
    Integer quantity // 상품 수량.
) {
}
