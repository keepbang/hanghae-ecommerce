package com.hhplus.commerce.app.init;

import com.hhplus.commerce.app.order.repository.OrderItemJpaRepository;
import com.hhplus.commerce.app.order.repository.OrderJpaRepository;
import com.hhplus.commerce.app.product.dto.ProductRequest;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.repository.InventoryJpaRepository;
import com.hhplus.commerce.app.product.repository.ProductJpaRepository;
import com.hhplus.commerce.app.product.service.ProductService;
import com.hhplus.commerce.app.user.domain.User;
import com.hhplus.commerce.app.user.dto.ChargeRequest;
import com.hhplus.commerce.app.user.repository.UserJpaRepository;
import com.hhplus.commerce.app.user.repository.WalletJpaRepository;
import com.hhplus.commerce.app.user.service.WalletService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * create on 4/19/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

  // 상품
  @Autowired
  InventoryJpaRepository inventoryJpaRepository;
  @Autowired
  ProductJpaRepository productJpaRepository;
  @Autowired
  ProductService productService;

  // 사용자
  @Autowired
  UserJpaRepository userJpaRepository;
  @Autowired
  WalletJpaRepository walletJpaRepository;
  @Autowired
  WalletService walletService;

  // 주문
  @Autowired
  OrderJpaRepository orderJpaRepository;
  @Autowired
  OrderItemJpaRepository orderItemJpaRepository;

  private UUID userKey = UUID.randomUUID();

  @BeforeEach
  void setUp() {
    inventoryJpaRepository.deleteAll();
    productJpaRepository.deleteAll();
    userJpaRepository.deleteAll();
    walletJpaRepository.deleteAll();
    orderJpaRepository.deleteAll();
    orderItemJpaRepository.deleteAll();

    productService.save(new ProductRequest("장난감", 1L, 10));
    productService.save(new ProductRequest("바지", 1L, 10));
    productService.save(new ProductRequest("티셔츠", 1L, 10));
    productService.save(new ProductRequest("머그컵", 1L, 10));
    productService.save(new ProductRequest("마우스", 1L, 10));
    productService.save(new ProductRequest("키보드", 1L, 10));

    userJpaRepository.save(new User(userKey, "kim", "서울 강남구"));
    walletService.charge(new ChargeRequest(
        userKey, 20L
    ));
  }

  public UUID getUserKey() {
    return userKey;
  }

  public ProductResponse getProductResponse(Long productId) {
    return productService.findById(productId);
  }


}
