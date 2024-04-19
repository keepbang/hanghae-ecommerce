package com.hhplus.commerce.app.user.stub;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.hhplus.commerce.app.user.domain.User;
import com.hhplus.commerce.app.user.repository.UserRepository;
import java.util.UUID;

/**
 * create on 4/15/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubUserRepository implements UserRepository {

  User user;

  public StubUserRepository(UUID userKey) {
    user = new User(
        userKey,
        "kim",
        "서울 강남구"
    );
  }

  @Override
  public User findByUserKeyOrThrows(UUID userKey) {
    return this.user;
  }
}
