package com.hhplus.commerce.app.product.stub;

import com.hhplus.commerce.app.product.dto.ProductResponse;
import com.hhplus.commerce.app.product.service.ReadProductQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubReadProductQuery implements ReadProductQuery {

  List<ProductResponse> productResponses;

  public StubReadProductQuery(List<ProductResponse> productResponses) {
    this.productResponses = productResponses;
  }

  @Override
  public ProductResponse findById(Long id) {
    return this.productResponses.stream()
        .filter(product -> product.productId().equals(id))
        .findFirst()
        .get();
  }
}
