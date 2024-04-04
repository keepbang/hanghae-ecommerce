package com.hhplus.commerce.app.order.dto;

/**
 * create on 4/4/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public enum RecommendType {
  RECOMMEND_01("최근 3일간 가장 많이 팔린 상품")
  ;

  private final String description;

  RecommendType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
