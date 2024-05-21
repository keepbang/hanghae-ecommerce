package com.hhplus.commerce.app.user.service;

import com.hhplus.commerce.app.user.dto.UserResponse;
import java.util.UUID;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 사용자 검색 interface </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface UserQuery {

  Long getUserIdByUserKey(UUID userKey);

  UserResponse findByUserKey(UUID userKey);

}
