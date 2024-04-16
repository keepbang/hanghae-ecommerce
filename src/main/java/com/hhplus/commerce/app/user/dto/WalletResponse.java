package com.hhplus.commerce.app.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 사용자 잔액 Response </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record WalletResponse(
    UUID userKey,
    Long balance,
    LocalDateTime updateAt
) {

}
