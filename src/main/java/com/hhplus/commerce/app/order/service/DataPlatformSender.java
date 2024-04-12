package com.hhplus.commerce.app.order.service;

import com.hhplus.commerce.app.order.dto.OrderRequest;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface DataPlatformSender {

  void send(OrderRequest request);

}
