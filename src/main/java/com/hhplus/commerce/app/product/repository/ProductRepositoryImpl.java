package com.hhplus.commerce.app.product.repository;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import com.hhplus.commerce.app.product.domain.Product;
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
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;

  public ProductRepositoryImpl(ProductJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
  }

  @Override
  public Product save(Product product) {
    return productJpaRepository.save(product);
  }

  @Override
  public Product findByIdOrThrows(Long id) {
    return productJpaRepository.findById(id)
        .orElseThrow(NotFoundException::new);
  }
}
