package com.hhplus.commerce.app.order.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.common.redis.RedissonConfig;
import com.hhplus.commerce.app.common.type.PaymentType;
import com.hhplus.commerce.app.order.domain.Order;
import com.hhplus.commerce.app.order.domain.OrderItem;
import com.hhplus.commerce.app.order.domain.OrderItemId;
import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.order.dto.OrderRequest;
import com.hhplus.commerce.app.order.stub.StubOrderItemRepository;
import com.hhplus.commerce.app.order.stub.StubOrderRepository;
import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import com.hhplus.commerce.app.product.service.InventoryService;
import com.hhplus.commerce.app.product.service.ProductValidator;
import com.hhplus.commerce.app.product.stub.StubInventoryRepository;
import com.hhplus.commerce.app.user.dto.ChargeRequest;
import com.hhplus.commerce.app.user.dto.WalletResponse;
import com.hhplus.commerce.app.user.repository.WalletHistoryRepository;
import com.hhplus.commerce.app.user.repository.WalletRepository;
import com.hhplus.commerce.app.user.service.ReadUserQuery;
import com.hhplus.commerce.app.user.service.UserService;
import com.hhplus.commerce.app.user.service.WalletService;
import com.hhplus.commerce.app.user.service.WalletValidator;
import com.hhplus.commerce.app.user.stub.StubUserRepository;
import com.hhplus.commerce.app.user.stub.StubWalletHistoryRepository;
import com.hhplus.commerce.app.user.stub.StubWalletRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 4/16/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class OrderServiceTest {

  private OrderService orderService;
  private StubOrderRepository orderRepository = new StubOrderRepository();
  private StubOrderItemRepository orderItemRepository = new StubOrderItemRepository();

  private InventoryRepository inventoryRepository = new StubInventoryRepository();
  private InventoryService inventoryService = new InventoryService(
      inventoryRepository,
      new ProductValidator()
  );

  UUID userKey = UUID.randomUUID();
  private ReadUserQuery readUserQuery = new UserService(new StubUserRepository(userKey));
  private WalletRepository walletRepository = new StubWalletRepository();
  private WalletHistoryRepository walletHistoryRepository = new StubWalletHistoryRepository();
  private WalletService walletService = new WalletService(
      readUserQuery,
      walletRepository,
      walletHistoryRepository,
      new WalletValidator()
  );

  @BeforeEach
  void setUp() {
    this.orderService = new OrderService(
        orderRepository, orderItemRepository,
        walletService, inventoryService, new DataPlatformSenderImpl()
    );
  }

  @DisplayName("order(): 주문 완료 시 포인트와 재고가 차감되고 주문 정보가 저장된다.")
  @Test
  void order_ok() {
    // given
    // 상품 재고 등록
    inventoryRepository.save(new Inventory(
        1L,
        1
    ));

    inventoryRepository.save(new Inventory(
        2L,
        1
    ));

    // 사용자 지갑 충전
    ChargeRequest chargeRequest = new ChargeRequest(
        userKey,
        5L
    );

    walletService.charge(chargeRequest);

    // 주문 요청 생성
    OrderRequest request = new OrderRequest(
        userKey,
        PaymentType.WALLET,
        "서울 강남구",
        LocalDateTime.now(),
        List.of(
            new OrderItemRequest(1L, 2L, 1),
            new OrderItemRequest(2L, 3L, 1)
        )
    );

    // when
    orderService.order(request);

    // then
    Order order = orderRepository.getOrder(1L);
    Map<OrderItemId, OrderItem> orderItemMap = orderItemRepository.getOrderItemMap();

    // 주문 검증
    assertThat(order.getTransactionId()).isNotNull();
    assertThat(order.getTotalPrice()).isEqualTo(5L);
    assertThat(order.getProductQuantity()).isEqualTo(2);
    assertThat(orderItemMap.size()).isEqualTo(2);

    // 지갑 검증
    WalletResponse userWallet = walletService.getUserWallet(userKey);
    assertThat(userWallet.balance()).isZero();

    // 재고 검증
    Inventory product1 = inventoryRepository.findById(1L);
    Inventory product2 = inventoryRepository.findById(2L);

    assertThat(product1.getCurrentStock()).isZero();
    assertThat(product2.getCurrentStock()).isZero();

  }

}