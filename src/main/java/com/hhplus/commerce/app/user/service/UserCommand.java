package com.hhplus.commerce.app.user.service;

import com.hhplus.commerce.app.user.dto.UserCreateRequest;
import com.hhplus.commerce.app.user.dto.UserResponse;

public interface UserCommand {
    UserResponse save(UserCreateRequest request);
}
