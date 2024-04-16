package com.hhplus.commerce.app.product.stub;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import com.hhplus.commerce.app.product.domain.Product;
import com.hhplus.commerce.app.product.repository.ProductRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubProductRepository implements ProductRepository {

  Map<Long, Product> productMap = new HashMap<>();

  Long curr = 1L;

  @Override
  public Product save(Product product) {
    Long productId = curr++;
    Product newProduct = new Product(
        productId,
        product.getName(),
        product.getPrice()
    );
    productMap.put(productId, newProduct);
    return newProduct;
  }

  @Override
  public Product findByIdOrThrows(Long id) {
    return Optional.ofNullable(productMap.get(id))
        .orElseThrow(NotFoundException::new);
  }
}
