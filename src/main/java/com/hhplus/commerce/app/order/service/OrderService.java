package com.hhplus.commerce.app.order.service;

import com.hhplus.commerce.app.common.type.OrderStatus;
import com.hhplus.commerce.app.order.domain.Order;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.OrderItemId;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.repository.OrderItemRepository;
import com.hhplus.commerce.app.order.repository.OrderRepository;
import com.hhplus.commerce.app.product.service.InventoryService;
import com.hhplus.commerce.app.wallet.dto.UseRequest;
import com.hhplus.commerce.app.wallet.service.WalletService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;


  private final WalletService walletService;
  private final InventoryService inventoryService;

  @Transactional
  public void order(OrderRequest request) {

    // 결제
    String transactionId = walletService.useWallet(
        new UseRequest(
            request.userKey(),
            request.totalPrice())
    );

    // 재고차감
    request.orderItems()
        .forEach(inventoryService::orderItemDeduction);

    // 주문
    Order order = new Order(
        UUID.randomUUID(),
        request.userKey(),
        request.totalPrice(),
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
            OrderStatus.ORDER_COMPLETED
        ))
        .toList();

    orderItemRepository.saveAll(itemList);

  }

}
