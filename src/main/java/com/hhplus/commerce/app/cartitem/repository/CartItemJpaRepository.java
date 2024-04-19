package com.hhplus.commerce.app.cartitem.repository;

import com.hhplus.commerce.app.cartitem.domain.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 4/18/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

  void deleteByUserSeqIdAndProductId(Long userSeqId, Long productId);
  List<CartItem> findAllByUserSeqId(Long userSeqId);
}
