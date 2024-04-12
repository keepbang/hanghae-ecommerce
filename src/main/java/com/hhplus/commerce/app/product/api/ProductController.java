package com.hhplus.commerce.app.product.api;

import com.hhplus.commerce.app.product.dto.ProductRequest;
import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;


  /**
   * 특정 상품 조회.
   *
   * @param id 상품 아이디.
   * @return 상품 정보 response
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(
        productService.findById(id),
        HttpStatus.OK
    );
  }

  @PostMapping("")
  public ResponseEntity<Void> createProduct(
      @RequestBody ProductRequest request
  ) {
    productService.save(request);

    return new ResponseEntity<>(
        HttpStatus.CREATED
    );
  }

}
