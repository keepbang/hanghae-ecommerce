package com.hhplus.commerce.app.wallet.api;

import com.hhplus.commerce.app.wallet.dto.ChargeRequest;
import com.hhplus.commerce.app.wallet.dto.WalletResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 4/2/24. create by IntelliJ IDEA.
 *
 * <p> 사용자 지갑 관련 API </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {

  /**
   * 특정 사용자 잔액 조회.
   *
   * @param id 사용자 아이디.
   * @return 잔액 정보 response.
   */
  @GetMapping("/users/{id}")
  public ResponseEntity<WalletResponse> getUserWallet(
      @PathVariable("id") Long id) {
    return new ResponseEntity<>(
        new WalletResponse(id, 10000L, LocalDateTime.now()),
        HttpStatus.OK
    );
  }

  /**
   * 특정 사용자 잔액 충전.
   *
   * @param request 사용자 아이디, 충전할 금액 이 담긴 request.
   */
  @PatchMapping("/charge")
  public ResponseEntity<Void> charge(
      @RequestBody ChargeRequest request
  ) {
    // todo : 충전 및 충전 history 저장
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
