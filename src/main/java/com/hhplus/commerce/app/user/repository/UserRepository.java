package com.hhplus.commerce.app.user.repository;

import com.hhplus.commerce.app.user.domain.User;
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
public interface UserRepository {

  User findByUserKeyOrThrows(UUID userKey);

}
