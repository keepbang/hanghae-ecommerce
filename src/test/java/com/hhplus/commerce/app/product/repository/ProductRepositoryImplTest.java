package com.hhplus.commerce.app.product.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * create on 4/11/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class ProductRepositoryImplTest {

  @MockBean
  private ProductJpaRepository productJpaRepository;

  @InjectMocks
  private ProductRepositoryImpl productRepository;

  @Test
  @DisplayName("")
  public void findById_NotFound() throws Exception {
    //given

    //when

    //then

  }

}