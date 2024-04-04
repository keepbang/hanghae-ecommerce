package com.hhplus.commerce.app.order.dto;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 주문한 상품 정보. </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record OrderItemRequest(
    Long productId, // 주문 상품 아이디.
    Long price, // 결제 금액.
    Integer quantity // 결제 수량.
){
}
