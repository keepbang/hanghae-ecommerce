package com.hhplus.commerce.app.order.dto;

import com.hhplus.commerce.app.common.type.PaymentType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    UUID userKey, // 사용자 아이디(uuid)
    Long totalPrice, // 총 결제금액
    PaymentType paymentType, // 결제 타입
    String address, // 배송지
    LocalDateTime orderAt, // 결제 일시

    List<OrderItemRequest> orderItems // 주문 상품 리스트.

) {

}
