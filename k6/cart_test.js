import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    scenarios: {
        contacts: {
            executor: 'per-vu-iterations', //각 사용자에 대해 지정한 수 만큼 반복 수행하도록 함
            vus: 10, //가상 사용자
            iterations: 20, //각 가상 사용자가 반복 수행할 횟수
            maxDuration: '30s', //시나리오의 최대 실행 시간
        },
    },
};

const BASE_URL = 'http://localhost:8080';

export function setup() {
    // 사용자 추가
    const userRequest = {
        name: 'kim',
        address: 'seoul'
    };

    const res = http.post(`${BASE_URL}/users`, JSON.stringify(userRequest), {
        headers: { 'Content-Type': 'application/json' },
    });

    let userKey = JSON.parse(res.body).userKey;

    check(res, {
        'userKey is not null': (r) => (JSON.parse(r.body).userKey !== undefined
            && JSON.parse(r.body).userKey !== null),
    })

    // 상품 추가

    const product1Request = {
        name: "바지",
        price: 1,
        stock: 100
    };

    const product2Request = {
        name: "셔츠",
        price: 1,
        stock: 100
    };

    const product1 = http.post(`${BASE_URL}/products`, JSON.stringify(product1Request), {
        headers: { 'Content-Type': 'application/json' },
    });

    const product2 =http.post(`${BASE_URL}/products`, JSON.stringify(product2Request), {
        headers: { 'Content-Type': 'application/json' },
    });

    // 장바구니 추가
    const cart1 = {
        userKey: `${userKey}`,
        productId: 1,
        quantity: 3,
    }

    const cart2 = {
        userKey: `${userKey}`,
        productId: 2,
        quantity: 3,
    }

    const cart1Res = http.put(`${BASE_URL}/carts`, JSON.stringify(cart1), {
        headers: { 'Content-Type': 'application/json' },
    });

    const cart2Res =http.put(`${BASE_URL}/carts`, JSON.stringify(cart2), {
        headers: { 'Content-Type': 'application/json' },
    });

    check(cart1Res, {
        'add to cart1 is status 200': (r) => r.status === 200,
    })

    check(cart2Res, {
        'add to cart2 is status 200': (r) => r.status === 200,
    })

    return userKey

}

export default function (userKey) {

    http.get(`${BASE_URL}/carts/users/${userKey}`, {
        headers: { 'Content-Type': 'application/json' },
    });

    sleep(0.5);
}