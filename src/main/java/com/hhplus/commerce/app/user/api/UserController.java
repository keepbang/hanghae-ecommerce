package com.hhplus.commerce.app.user.api;

import com.hhplus.commerce.app.user.dto.UserCreateRequest;
import com.hhplus.commerce.app.user.dto.UserResponse;
import com.hhplus.commerce.app.user.service.UserCommand;
import com.hhplus.commerce.app.user.service.UserQuery;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserQuery userQuery;
  private final UserCommand userCommand;

  /**
   * 사용자 조회.
   *
   * @param userKey 사용자 아이디(uuid).
   * @return 사용자 정보 response.
   */
  @GetMapping("/{key}")
  public ResponseEntity<UserResponse> getUserWallet(
      @PathVariable("key") UUID userKey) {
    return new ResponseEntity<>(
        userQuery.findByUserKey(userKey),
        HttpStatus.OK
    );
  }

  /**
   * 사용자 저장.
   *
   * @param request 사용자 정보 request.
   */
  @PostMapping
  public ResponseEntity<UserResponse> save(
      @RequestBody UserCreateRequest request
  ) {

    return new ResponseEntity<>(
        userCommand.save(request),
        HttpStatus.OK
    );
  }

}
