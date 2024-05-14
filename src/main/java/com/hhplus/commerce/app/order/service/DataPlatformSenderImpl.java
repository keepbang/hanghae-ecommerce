package com.hhplus.commerce.app.order.service;

import com.hhplus.commerce.app.order.dto.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
public class DataPlatformSenderImpl implements DataPlatformSender {

  @Async
  @Override
  public void send(OrderRequest request) {
    // todo : 외부 API 연동
    log.debug("call external api");
      try {
          Thread.sleep((long) (Math.random() * 1000));
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }
}
