package com.hhplus.commerce.app.wallet.repository;

import com.hhplus.commerce.app.wallet.domain.User;
import java.util.Optional;
import java.util.UUID;
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
public interface UserJpaRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserKey(UUID userKey);

}
