package com.hhplus.commerce.app.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.app.user.domain.Wallet;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * create on 4/12/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class WalletRepositoryImplTest {

  @Mock
  private WalletJpaRepository walletJpaRepository;

  @InjectMocks
  private WalletRepositoryImpl walletRepository;

  @Test
  @DisplayName("지갑 정보가 존재하지 않을 경우 잔액 0으로 반환")
  public void findByUserId_notFoundWallet() throws Exception {
    //given
    given(walletJpaRepository.findByUserId(anyLong()))
        .willReturn(Optional.empty());
    //when
    Wallet wallet = walletRepository.findByUserId(1L);
    //then
    assertThat(wallet.getBalance()).isEqualTo(0L);
  }


}