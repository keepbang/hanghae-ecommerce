package com.hhplus.commerce.app.common.type;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public enum OrderStatus {
  PAID, // 결제 완료 까지 된 상태
  ORDER_COMPLETED, // 주문 완료
  DELIVERY_WAIT, // 배송 대기
  DELIVERY, // 배송중
  DELIVERY_COMPLETED, // 배송 완료
  ORDER_CANCELED, // 주문 취소
;
}
