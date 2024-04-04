package com.hhplus.commerce.app.cartitem.dto;

import java.time.LocalDateTime;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record CartItemResponse(
    Long productId, // 상품 아이디.
    String productName, // 상품 이름.
    Integer quantity, // 장바구니 상품 수량
    Long price, // 상품 가격
    LocalDateTime lastUpdateAt // 마지막 업데이트 시간.

) {

}
