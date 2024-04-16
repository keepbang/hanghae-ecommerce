package com.hhplus.commerce.app.user.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import java.util.Optional;
import java.util.UUID;
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
class UserRepositoryImplTest {

  @Mock
  private UserJpaRepository userJpaRepository;

  @InjectMocks
  private UserRepositoryImpl userRepository;

  @Test
  @DisplayName("사용자가 없을 경우 exception")
  public void findByUserKey_NotFound() throws Exception {
    //given
    given(userJpaRepository.findByUserKey(any()))
        .willReturn(Optional.empty());

    //when
    //then
    assertThatThrownBy(() -> userRepository.findByUserKeyOrThrows(UUID.randomUUID()))
        .isInstanceOf(NotFoundException.class);

  }


}