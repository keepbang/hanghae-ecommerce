package com.hhplus.commerce.app.product.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.domain.Product;
import com.hhplus.commerce.app.product.dto.ProductRequest;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import com.hhplus.commerce.app.product.repository.ProductRepository;
import com.hhplus.commerce.app.product.stub.StubInventoryRepository;
import com.hhplus.commerce.app.product.stub.StubProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class ProductServiceTest {

  private ProductService productService;
  private ProductRepository productRepository = new StubProductRepository();
  private InventoryRepository inventoryRepository = new StubInventoryRepository();

  @BeforeEach
  void setUp() {
    this.productService = new ProductService(
        productRepository,
        inventoryRepository,
        new ProductValidator());
  }

  @DisplayName("save(): 상품 저장시 재고 테이블에 상품 재고가 저장된다.")
  @Test
  void save_ok() {
    // given
    ProductRequest request = new ProductRequest(
        "청바지",
        1L,
    1
    );

    // when
    productService.save(request);

    // then
    Product product = productRepository.findByIdOrThrows(1L);
    Inventory inventory = inventoryRepository.findById(1L);

    assertThat(product.getName()).isEqualTo("청바지");
    assertThat(inventory.getProductId()).isEqualTo(product.getProductId());
  }

  @DisplayName("findById(): 상품 조회시 재고와 함께 조회된다.")
  @Test
  void findById_ok() {
    // given
    ProductRequest request = new ProductRequest(
        "청바지",
        1L,
        1
    );

    productService.save(request);

    // when
    ProductResponse response = productService.findById(1L);

    // then
    assertThat(response.name()).isEqualTo("청바지");
    assertThat(response.stock()).isEqualTo(1);
  }

}