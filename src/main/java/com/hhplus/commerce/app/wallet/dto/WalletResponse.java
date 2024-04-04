package com.hhplus.commerce.app.wallet.dto;

import java.time.LocalDateTime;

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
    Long userId,
    Long balance,
    LocalDateTime updateAt
) {

}
