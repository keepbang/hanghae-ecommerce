package com.hhplus.commerce.app.user.repository;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import com.hhplus.commerce.app.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

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
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  @Override
  @Cacheable(cacheNames = "USER_KEY",
          key = "#userKey.toString()",
          condition = "#userKey != null",
          cacheManager = "cacheManager")
  public User findByUserKeyOrThrows(UUID userKey) {
    return userJpaRepository.findByUserKey(userKey)
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public User save(User user) {
    return userJpaRepository.save(user);
  }
}
