package com.hhplus.commerce.app.product.dto;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record ProductRequest(
    String name,
    Long price,
    Integer stock
) {

}
