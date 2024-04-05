package com.hhplus.commerce.app.product.api;

import com.hhplus.commerce.app.product.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 4/2/24. create by IntelliJ IDEA.
 *
 * <p> 상품 관련 API </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  /**
   * 특정 상품 조회.
   *
   * @param id 상품 아이디.
   * @return 상품 정보 response
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(
        new ProductResponse(id, "상품 이름", 1000L, 20),
        HttpStatus.OK
    );
  }

}
