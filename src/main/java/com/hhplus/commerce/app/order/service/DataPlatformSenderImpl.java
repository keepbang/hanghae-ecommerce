package com.hhplus.commerce.app.order.service;

import com.hhplus.commerce.app.order.dto.OrderRequest;
import org.springframework.stereotype.Component;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public class DataPlatformSenderImpl implements DataPlatformSender {

  @Override
  public void send(OrderRequest request) {
    // todo : 외부 API 연동
  }
}
