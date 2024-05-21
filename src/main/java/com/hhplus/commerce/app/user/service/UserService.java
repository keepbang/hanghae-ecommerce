package com.hhplus.commerce.app.user.service;

import com.hhplus.commerce.app.user.domain.User;
import com.hhplus.commerce.app.user.dto.UserCreateRequest;
import com.hhplus.commerce.app.user.dto.UserResponse;
import com.hhplus.commerce.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
public class UserService implements UserQuery, UserCommand {

  private final UserRepository userRepository;

  @Override
  public Long getUserIdByUserKey(UUID userKey) {
    return userRepository.findByUserKeyOrThrows(userKey)
        .getId();
  }

  @Override
  public UserResponse findByUserKey(UUID userKey) {
    User findUser = userRepository.findByUserKeyOrThrows(userKey);
    return new UserResponse(
        findUser.getUserKey(),
        findUser.getName(),
        findUser.getAddress()
    );
  }

  @Override
  public UserResponse save(UserCreateRequest request) {
    User savedUser = userRepository.save(
        new User(
            UUID.randomUUID(),
            request.name(),
            request.address()
        )
    );
    return new UserResponse(
        savedUser.getUserKey(),
        savedUser.getName(),
        savedUser.getAddress()
    );
  }
}
