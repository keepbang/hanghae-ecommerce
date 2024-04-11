package com.hhplus.commerce.app.product.repository;

import com.hhplus.commerce.app.product.domain.Inventory;
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
public class InventoryRepositoryImpl implements InventoryRepository {

  private final InventoryJpaRepository inventoryJpaRepository;

  public InventoryRepositoryImpl(InventoryJpaRepository inventoryJpaRepository) {
    this.inventoryJpaRepository = inventoryJpaRepository;
  }

  @Override
  public Inventory findById(Long productId) {
    return inventoryJpaRepository.findById(productId)
        .orElse(new Inventory(productId, 0));
  }

  @Override
  public Inventory save(Inventory inventory) {
    return inventoryJpaRepository.save(inventory);
  }
}
