package com.hhplus.commerce.app.user.repository;

import com.hhplus.commerce.app.user.domain.WalletHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface WalletHistoryJpaRepository extends JpaRepository<WalletHistory, Long> {

  List<WalletHistory> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}