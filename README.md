# E-Commerce Service 서버 구축

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/keepbang/hanghae-ecommerce)](https://hits.seeyoufarm.com)

# Hanghae Plus Practice

### 프로젝트 개요 ✨

> [항해플러스 백엔드](https://hanghae99.spartacodingclub.kr/v2/plus/be?&utm_source=google&utm_medium=bs&utm_campaign=hhplus&utm_content=brand&utm_term=%ED%95%AD%ED%95%B4%ED%94%8C%EB%9F%AC%EC%8A%A4&gcl_keyword=%ED%95%AD%ED%95%B4%ED%94%8C%EB%9F%AC%EC%8A%A4&gcl_network=g&gad_source=1&gclid=Cj0KCQjwk6SwBhDPARIsAJ59Gwc6jHzEOp6MAN9Gf2EQ_5CtBQgSYich9YHnEgPYGWmf9HohEmYD7OcaAvahEALw_wcB)
> 4기 과제

### 목표 🚀

> E-Commerce 서비스를 제공하는 애플리케이션 구축  
> 요구사항을 분석하여 프로젝트 설계 및 개발 진행  
> 테스트 작성이 가능하고 유연한 아키텍처 구성

### Environment

- Spring Boot 3.2.4  
- Java 17  
- Gradle 8.7  
- JPA  
- JUnit + AssertJ  
- H2 Database  

---

# Milestone
![milestone](images/img.png)

# ERD
![img.png](images/erd.png)

# API Spec

---

## 상품

### 상품 조회 API

- 요구사항 
  - 상품 아이디로 상품 정보 및 잔여수량을 조회한다.

<br/>

`Endpoint`
```
GET http://{host}/products/{id}
```
<br/>


`Request` **Path variable** 

| 파라미터   | 타입      | 필수여부 | 설명     |
|--------|---------|------|--------|
| id     | integer | Y    | 상품 아이디 |


`Response` **Response body**

| 파라미터      | 타입      | 필수여부 | 설명     |
|-----------|---------|------|--------|
| productId | integer | Y    | 상품 아이디 |
| name      | string  | Y    | 상품 이름  |
| price     | integer | Y    | 상품 가격  |
| inventory | integer | Y    | 잔여수량   |

`프로세스`
  - 상품 아이디로 상품 테이블에서 데이터 조회
    - 상품 아이디가 없을 경우 Exception 발생
    - 상품이 존재할 경우 `response`로 변환해서 반환함


`CURL`
```
curl --location --request GET 'http://localhost:8080/products/1'
```

---

## 주문

### 상품 주문 API

- 요구사항
  - 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행
  - 결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
  - 데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다.

<br/>

`Endpoint`
```
GET http://{host}/products/{id}
```
<br/>


`Request` **Request Body**

| 파라미터                   | 타입       | 필수여부 | 설명        |
|------------------------|----------|------|-----------|
| userId                 | integer  | Y    | 사용자 아이디   |
| totalPrice             | integer  | Y    | 총 결제 금액   |
| paymentType            | string   | Y    | 결제 타입     |
| orderAt                | datetime | Y    | 결제 일시     |
| OrderItems             | Array    | Y    | 주문 상품 리스트 |
| OrderItems[].productId | integer  | Y    | 상품 아이디    |
| OrderItems[].price     | price    | Y    | 결제 금액     |
| OrderItems[].quantity  | quantity | Y    | 결제 수량     |


`Response` **Response body**

| 파라미터      | 타입      | 필수여부 | 설명     |
|-----------|---------|------|--------|



`프로세스`
  - 성공일 경우 `200`
  - 주문 요청에 총 결제 금액으로 지갑에서 차감 시도
    - 잔액이 부족하면 `Exception` 발생
    - 


`CURL`
```
curl --location --request GET 'http://localhost:8080/products/1'

```


---

## 사용자 지갑

---

## 장바구니