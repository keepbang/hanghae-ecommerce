package com.hhplus.commerce.app.product.service;

import com.hhplus.commerce.app.product.dto.ProductResponse;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface ReadProductQuery {

  ProductResponse findById(Long id);

}
