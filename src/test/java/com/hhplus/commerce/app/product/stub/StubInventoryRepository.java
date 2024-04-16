package com.hhplus.commerce.app.product.stub;

import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubInventoryRepository implements InventoryRepository {

  Map<Long, Inventory> inventoryMap = new HashMap<>();

  @Override
  public Inventory findById(Long productId) {
    return inventoryMap.getOrDefault(productId,
        new Inventory(productId, 0));
  }

  @Override
  public Inventory save(Inventory inventory) {
    return inventoryMap.put(inventory.getProductId(), inventory);
  }
}
