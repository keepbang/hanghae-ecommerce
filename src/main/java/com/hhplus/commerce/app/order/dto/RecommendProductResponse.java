package com.hhplus.commerce.app.order.dto;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record RecommendProductResponse(
    Integer rank,
    Long productId,
    String name,
    Long orderCount
) {

}
