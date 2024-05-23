package com.hhplus.commerce.app.order.service;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.common.type.RecommendType;
import com.hhplus.commerce.app.order.domain.Order;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.OrderItemId;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.dto.RecommendProductResponse;
import com.hhplus.commerce.app.order.event.OrderEvent;
import com.hhplus.commerce.app.order.repository.OrderItemRepository;
import com.hhplus.commerce.app.order.repository.OrderRepository;
import com.hhplus.commerce.app.product.service.InventoryService;
import com.hhplus.commerce.app.user.dto.UseRequest;
import com.hhplus.commerce.app.user.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  private final WalletService walletService;
  private final InventoryService inventoryService;

  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public void order(OrderRequest request) {

    // 결제
    String transactionId = walletService.use(
        new UseRequest(
            request.userKey(),
            request.getTotalPrice())
    );

    // 재고차감 (ORDER_COMPLETED 상태로 변경)
    request.orderItems()
        .forEach(order ->
                inventoryService.orderItemDeduction(order.productId().toString(), order)
        );

    // 주문
    Order order = new Order(
        UUID.randomUUID(),
        request.userKey(),
        request.getTotalPrice(),
        request.orderItems().size(),
        request.address(),
        request.paymentType(),
        transactionId,
        request.orderAt()
    );

    Order savedOrder = orderRepository.save(order);

    List<OrderItem> itemList = request.orderItems()
        .stream()
        .map(item -> new OrderItem(
            new OrderItemId(
                savedOrder.getSeqId(),
                item.productId()
            ),
            item.price(),
            item.quantity(),
            OrderStatus.PAID,
            request.orderAt()
        ))
        .toList();

    orderItemRepository.saveAll(itemList);

    // 데이터 플랫폼 전달
    eventPublisher.publishEvent(new OrderEvent(request));

  }

  @Cacheable(cacheNames = "RECOMMEND_PRODUCT",
          key = "#type.name()",
          condition = "#type != null",
          cacheManager = "cacheManager")
  public List<RecommendProductResponse> getRecommendProduct(RecommendType type) {
    return switch(type) {
      case RECOMMEND_01 -> orderItemRepository.getRecommendProduct(
          OrderStatus.PAID,
          LocalDateTime.now().minusDays(3L),
          LocalDateTime.now()
      );
    };
  }

  @Scheduled(fixedDelay = 300000) // 5분
  @CacheEvict(cacheNames = "RECOMMEND_PRODUCT",
          key = "'RECOMMEND_01'",
          cacheManager = "cacheManager")
  public void recommendProductType1Evict() {
    log.debug("Initialize RECOMMEND_01 type cache data");
  }

}
