package com.hhplus.commerce.app.product.repository;

import com.hhplus.commerce.app.product.domain.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {

  private final InventoryJpaRepository inventoryJpaRepository;

  @Override
  @Cacheable(cacheNames = "INVENTORY_PRODUCT_ID",
          key = "#productId",
          condition = "#productId != null",
          cacheManager = "cacheManager")
  public Inventory findById(Long productId) {
    return inventoryJpaRepository.findByProductId(productId)
        .orElse(new Inventory(productId, 0));
  }

  @Override
  @CacheEvict(cacheNames = "INVENTORY_PRODUCT_ID",
          key = "#inventory.productId",
          condition = "#inventory != null",
          cacheManager = "cacheManager")
  public Inventory save(Inventory inventory) {
    return inventoryJpaRepository.save(inventory);
  }
}
