package com.hhplus.commerce.app.product.dto;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 상품 조회 response </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record ProductResponse(
    Long productId,
    String name,
    Long price,
    Integer inventory
) {

}
