package com.hhplus.commerce.app.product.service;

import com.hhplus.commerce.app.order.dto.OrderItemRequest;
import com.hhplus.commerce.app.product.domain.Inventory;
import com.hhplus.commerce.app.product.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  private final ProductValidator productValidator;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void orderItemDeduction(OrderItemRequest request) {
    Integer quantity = request.quantity();

    productValidator.quantityValidation(quantity);

    Inventory inventory = inventoryRepository.findById(request.productId());

    Inventory deductedInventory = inventory.deduction(quantity);

    inventoryRepository.save(deductedInventory);

  }

}
