package com.hhplus.commerce.app.order.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 주문 정보 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record OrderRequest(
    Long userId, // 사용자 아이디
    Long totalPrice, // 총 결제금액
    String address, // 배송 주소
    PaymentType paymentType, // 결제 타입
    LocalDateTime orderAt, // 결제 일시

    List<OrderItemRequest> orderItems // 주문 상품 리스트.

) {

}
