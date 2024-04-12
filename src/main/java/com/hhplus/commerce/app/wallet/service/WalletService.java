package com.hhplus.commerce.app.wallet.service;

import com.hhplus.commerce.app.common.type.TransactionType;
import com.hhplus.commerce.app.wallet.domain.User;
import com.hhplus.commerce.app.wallet.domain.Wallet;
import com.hhplus.commerce.app.wallet.domain.WalletHistory;
import com.hhplus.commerce.app.wallet.dto.ChargeRequest;
import com.hhplus.commerce.app.wallet.dto.UseRequest;
import com.hhplus.commerce.app.wallet.dto.WalletResponse;
import com.hhplus.commerce.app.wallet.repository.UserRepository;
import com.hhplus.commerce.app.wallet.repository.WalletHistoryRepository;
import com.hhplus.commerce.app.wallet.repository.WalletRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
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
public class WalletService {

  private final UserRepository userRepository;
  private final WalletRepository walletRepository;
  private final WalletHistoryRepository walletHistoryRepository;

  private final WalletValidator walletValidator;

  public WalletResponse getUserWallet(UUID userKey) {
    Wallet wallet = walletRepository.findByUserId(getUserId(userKey));

    return new WalletResponse(
        userKey,
        wallet.getBalance(),
        wallet.getModifiedAt()
    );
  }

  @Transactional
  public void charge(ChargeRequest request) {
    walletValidator.amountValidation(request.amount());

    Long userId = getUserId(request.userKey());
    Wallet foundWallet = walletRepository.findByUserId(
        userId
    );

    Wallet chargedWallet = foundWallet.charge(request.amount());

    walletRepository.merge(chargedWallet);

    // history 저장
    saveHistory(userId, TransactionType.CHARGE, request.amount());

  }

  /**
   * 사용자 지갑 사용
   * @param request 요청 파라미터
   * @return  결제 트랜젝션 아이디.
   */
  @Transactional
  public String useWallet(UseRequest request) {
    walletValidator.amountValidation(request.amount());
    Long userId = getUserId(request.userKey());
    Wallet foundWallet = walletRepository.findByUserId(
        userId
    );

    Wallet usedWallet = foundWallet.use(request.amount());

    walletRepository.merge(usedWallet);

    // history 저장
    WalletHistory walletHistory = saveHistory(userId, TransactionType.USE, request.amount());

    // 사용자 지갑의 경우 id를 결제 트랜젝션 아이디로 사용
    return String.valueOf(walletHistory.getId());
  }


  private WalletHistory saveHistory(Long userId, TransactionType use, Long amount) {
    return walletHistoryRepository.save(new WalletHistory(
        userId,
        use,
        amount,
        LocalDateTime.now()
    ));
  }

  private Long getUserId(UUID userKey) {
    User user = userRepository.findByUserKeyOrThrows(userKey);
    return user.getId();
  }
}
