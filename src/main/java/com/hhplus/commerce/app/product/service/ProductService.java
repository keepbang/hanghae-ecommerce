package com.hhplus.commerce.app.product.service;

import com.hhplus.commerce.app.common.exception.OutOfStockException;
import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.domain.Product;
import com.hhplus.commerce.app.product.dto.ProductRequest;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import com.hhplus.commerce.app.product.repository.ProductJpaRepository;
import com.hhplus.commerce.app.product.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
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
public class ProductService {

  private final ProductRepository productRepository;
  private final InventoryRepository inventoryRepository;


  @Transactional
  public void save(ProductRequest request) {
    // 가격 검증
    ProductValidator.priceValidation(request.price());

    // 수량 검증
    ProductValidator.quantityValidation(request.stock());

    Product savedProduct = productRepository.save(
        new Product(
            request.name(),
            request.price()
        )
    );
    inventoryRepository.save(
        new Inventory(
            savedProduct.getProductId(),
            request.stock()
        )
    );
  }

  /**
   * 상품 조회.
   *
   * @param id  상품 아이디.
   * @return  상품 정보
   */
  public ProductResponse findById(Long id) {

    // 상품 조회
    Product product = productRepository.findByIdOrThrows(id);

    // 재고 조회
    Inventory inventory = inventoryRepository.findById(id);

    return new ProductResponse(
        product.getProductId(),
        product.getName(),
        product.getPrice(),
        inventory.getCurrentStock()
    );
  }
}
