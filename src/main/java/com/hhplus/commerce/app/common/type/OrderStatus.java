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
  ORDER_COMPLETED, // 결제완료까지 된 상태
  ORDER_CONFIRM, // 주문 확인
  DELIVERY_WAIT, // 배송 대기
  DELIVERY, // 배송중
  DELIVERY_COMPLETED; // 배송 완료


}
