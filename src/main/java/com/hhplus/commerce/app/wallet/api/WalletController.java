package com.hhplus.commerce.app.wallet.api;

import com.hhplus.commerce.app.wallet.dto.ChargeRequest;
import com.hhplus.commerce.app.wallet.dto.WalletResponse;
import com.hhplus.commerce.app.wallet.service.WalletService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class WalletController {

  private final WalletService walletService;

  /**
   * 특정 사용자 잔액 조회.
   *
   * @param userKey 사용자 아이디(uuid).
   * @return 잔액 정보 response.
   */
  @GetMapping("/users/{key}")
  public ResponseEntity<WalletResponse> getUserWallet(
      @PathVariable("key") UUID userKey) {
    return new ResponseEntity<>(
        walletService.getUserWallet(userKey),
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
    walletService.charge(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
