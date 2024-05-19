package com.hhplus.commerce.app.user.service;

import com.hhplus.commerce.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements ReadUserQuery {

  private final UserRepository userRepository;

  @Override
  public Long getUserIdByUserKey(UUID userKey) {
    return userRepository.findByUserKeyOrThrows(userKey)
            .getId();
  }
}
