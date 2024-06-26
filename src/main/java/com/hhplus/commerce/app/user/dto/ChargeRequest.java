package com.hhplus.commerce.app.user.dto;

import java.util.UUID;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record ChargeRequest(
    UUID userKey,
    Long amount
) {

}
