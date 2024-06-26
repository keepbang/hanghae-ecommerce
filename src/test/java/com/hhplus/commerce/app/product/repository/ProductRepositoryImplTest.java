package com.hhplus.commerce.app.product.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.app.common.exception.NotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

  @Mock
  private ProductJpaRepository productJpaRepository;

  @InjectMocks
  private ProductRepositoryImpl productRepository;

  @Test
  @DisplayName("찾는 상품이 없을 경우 exception")
  public void findById_NotFound() throws Exception {
    //given
    given(productJpaRepository.findById(anyLong()))
        .willReturn(Optional.empty());

    //when
    //then
    assertThatThrownBy(() -> productRepository.findByIdOrThrows(1L))
        .isInstanceOf(NotFoundException.class);

  }

}