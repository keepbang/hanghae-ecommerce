package com.hhplus.commerce.app.user.dto;

import java.util.UUID;

public record UserResponse(
        UUID userKey,
        String name,
        String address
) {
}
