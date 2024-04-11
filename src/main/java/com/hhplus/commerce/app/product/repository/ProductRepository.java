package com.hhplus.commerce.app.product.repository;

import com.hhplus.commerce.app.product.domain.Product;
import java.util.Optional;
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
public interface ProductRepository {

  Product save(Product product);

  Product findByIdOrThrows(Long id);

}
