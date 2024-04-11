package com.hhplus.commerce.app.product.repository;

import com.hhplus.commerce.app.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}

