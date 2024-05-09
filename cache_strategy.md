# 캐시 적용 목록

```java
@Cacheable(cacheNames = "USER_KEY",
      key = "#userKey.toString()",
      condition = "#userKey != null",
      cacheManager = "cacheManager")
public Long getUserIdByUserKey(UUID userKey) {
User user = userRepository.findByUserKeyOrThrows(userKey);
return user.getId();
}
```

- UUID로 되어있는 userKey로 사용자를 조회해서 사용한다.
- 데이터의 변경이 적고(UserKey와 userId는 거의 변경이 없음) 포인트 충전이나 사용, 주문을 할 때 빈번하게 사용되는 메소드여서 캐시를 적용

```java
  @Cacheable(cacheNames = "RECOMMEND_PRODUCT",
          key = "#type.name()",
          condition = "#type != null",
          cacheManager = "cacheManager")
  public List<RecommendProductResponse> getRecommendProduct(RecommendType type) {
    return switch(type) {
      case RECOMMEND_01 -> orderItemRepository.getRecommendProduct(
          OrderStatus.PAID,
          LocalDateTime.now().minusDays(3L),
          LocalDateTime.now()
      );
    };
  }

  @Scheduled(fixedDelay = 300000) // 5분
  @CacheEvict(cacheNames = "RECOMMEND_PRODUCT",
          key = "RECOMMEND_01",
          cacheManager = "cacheManager")
  public void recommendProductType1Evict() {
    log.debug("Initialize RECOMMEND_01 type cache data");
  }
```

- 추천상품 조회 시 타입에 따라 캐시 데이터 생성
- 스케줄러를 만들어서 해당 타입에 캐시 데이터를 주기적으로 초기화 해준다.

```java
  @Override
  @Cacheable(cacheNames = "INVENTORY_PRODUCT_ID",
          key = "#productId",
          condition = "#productId != null",
          cacheManager = "cacheManager")
  public Inventory findById(Long productId) {
    return inventoryJpaRepository.findByProductId(productId)
        .orElse(new Inventory(productId, 0));
  }

  @Override
  @CacheEvict(cacheNames = "INVENTORY_PRODUCT_ID",
          key = "#inventory.productId",
          condition = "#inventory != null",
          cacheManager = "cacheManager")
  public Inventory save(Inventory inventory) {
    return inventoryJpaRepository.save(inventory);
  }
```

- 상품 재고 조회 시 해당 상품 아이디로 캐시 데이터를 생성한다.
- 재고 수정 시 `save`를 사용하는데 그때 해당 상품 아이디의 캐시 데이터를 초기화한다. 