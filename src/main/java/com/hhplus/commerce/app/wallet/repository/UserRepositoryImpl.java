package com.hhplus.commerce.app.wallet.repository;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import com.hhplus.commerce.app.wallet.domain.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  @Override
  public User findByUserKeyOrThrows(UUID userKey) {
    return userJpaRepository.findByUserKey(userKey)
        .orElseThrow(NotFoundException::new);
  }
}
