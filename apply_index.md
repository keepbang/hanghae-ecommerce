# 인덱스(Index)

- 테이블의 열에 대한 데이터를 별도의 데이터 구조로 저장하고 이를 통해 빠른 검색 및 조회가 가능하다. 즉, 인덱스는 테이블의 열에 대한 추가적인 자료구조로 테이블과 함께 존재하면서 해당 테이블에 대한 빠른 검색을 지원한다.
- Order by나 Group by에도 index가 사용 될 수 있다.
- Foreign key 에는 인덱스가 자동으로 생성되지 않을 수 있다.(DB 종류에 따라 다름)
- 이미 많은 데이터가 존재할 경우 인덱스를 만들게 되면 시간이 몇 분 이상 소요될 수 있다.   
   -> 해당 테이블이 사용하는 서비스의 트래픽이 적은 시간대에 작업 해야함.

# 인덱스를 사용하는 이유
- 특정 조건의 데이터를 빠르게 찾기 위해서 사용한다.
- 조회 쿼리의 속도를 높일 수 있다.
- 빠르게 정렬을 하거나 그룹핑 하기 위해 사용 한다.

# B-tree index

- 기본적으로 특별한 경우가 아닐경우 B-tree 구조로 인덱스가 생성이된다.
- B-tree(Balance tree) 인덱스는 자식 노드의 개수가 가변적으로 변경된다.
- 트리구조로 구성되어 있고 인덱스 키를 기준으로 정렬되어 데이터가 구성 된다. 그리고 조회할 때에는 이진 탐색과 유사한 방식으로 조회한다.

### B-tree index 장점
- 데이터베이스에서 빠른 검색을 제공한다.
- 범위 쿼리(Range Query)를 지원하여 특정 범위의 데이터를 효율적으로 검색할 수 있다.
- 키를 정렬하여 저장하므로 정렬된 결과를 얻을 때 유용하다.

### B-tree index 주의사항
- 인덱스 생성 시 추가적인 저장 공간이 필요하며, 데이터 삽입 및 삭제 시에도 인덱스를 갱신해야 한다.
- 적절한 인덱스 디자인과 사용은 데이터베이스 성능에 큰 영향을 미치므로 주의해야 한다.

# 그 외 인덱스를 생성하는 방식
- Hash index
  - 해시함수를 통해 데이터를 저장하고 조회하는 방식
  - 빠른 속도로 데이터 조회 가능. 시간복잡도 O(n)
  - 데이터가 동일한지 아닌지만 비교 가능, 범위 검색은 불가능
  - 복함키 인덱스 사용시 조건에 모든 인덱스 키를 넣어야 index 사용이 가능하다.
- Bitmap Index
  - 인덱스 컬럼을 비트로 변환하여 사용하는 방식
  - 주로 카디널리티가 낮은 경우(중복도가 높은 경우) 사용함
  - 인덱스 데이터가 bit로 저장되기 때문에 공간 효율이 좋다.
- R-tree Index
  - 공간 데이터를 저장하고 검색하는데 사용
  - 다차원 공간 데이터를 효율적으로 관리하고 범위검색 및 근접한 데이터 검색이 가능하다.


# 인덱스 적용
- 최근 3일간 가장 많이 주문한 상품 5개를 조회하는 쿼리에 인덱스를 적용해 보고자 한다.
  
- QueryDSL 코드
```java
public List<RecommendProductResponse> getRecommendProduct(OrderStatus status,
                                                          LocalDateTime startAt, LocalDateTime endAt) {
  QOrderItem orderItem = QOrderItem.orderItem;
  QProduct product = QProduct.product;
  return queryFactory.select(
                  Projections.constructor(RecommendProductResponse.class,
                          orderItem.orderItemId.productId,
                          product.name,
                          orderItem.orderItemId.productId.count()
                  ))
          .from(orderItem)
          .innerJoin(product)
          .on(orderItem.orderItemId.productId.eq(product.productId))
          .where(orderItem.orderStatus.eq(status)
                  .and(orderItem.orderAt.between(startAt, endAt)))
          .groupBy(orderItem.orderItemId.productId)
          .orderBy(orderItem.orderItemId.productId.count().desc())
          .limit(5)
          .fetch();
}
```

- `where` 절에서 사용하는 `orderStatus`와 `orderAt`에 `index`를 설정해주었다.
- `OrderItem` 엔티티에 `index`를 설정 했다. 
```java
@Table(name = "order_item",
        indexes = @Index(
                name = "idx_status_order_at",
                columnList = "order_status,order_at"
        )
)
public class OrderItem {
  //...
}
```

