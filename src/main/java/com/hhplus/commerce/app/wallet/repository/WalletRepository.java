package com.hhplus.commerce.app.wallet.repository;

import com.hhplus.commerce.app.wallet.domain.Wallet;
import java.util.UUID;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface WalletRepository {

  Wallet findByUserId(Long userId);

  Wallet merge(Wallet wallet);
}