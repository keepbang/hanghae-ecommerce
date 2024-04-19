package com.hhplus.commerce.app.user.service;

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
public interface ReadUserQuery {

  Long getUserIdByUserKey(UUID userKey);

}
