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

function productOrder(userKey, productId, quantity) {
    return {
        userKey: `${userKey}`,
        paymentType: "WALLET",
        address: "강남",
        orderAt: "2024-05-23T09:34:45.860321",
        orderItems: [
            {
                productId: productId,
                price: 1,
                quantity: quantity,
            }
        ]
    }
}

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

    // 잔고 충전

    const walletRequest = {
        userKey: `${userKey}`,
        amount: 1600
    }

    http.patch(`${BASE_URL}/wallet/charge`, JSON.stringify(walletRequest), {
        headers: { 'Content-Type': 'application/json' },
    });

    // 상품 추가

    http.post(`${BASE_URL}/products`, JSON.stringify({
        name: "상품1",
        price: 1,
        stock: 1000
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    http.post(`${BASE_URL}/products`, JSON.stringify({
        name: "상품2",
        price: 1,
        stock: 1000
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    http.post(`${BASE_URL}/products`, JSON.stringify({
        name: "상품3",
        price: 1,
        stock: 1000
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    http.post(`${BASE_URL}/products`, JSON.stringify({
        name: "상품4",
        price: 1,
        stock: 1000
    }), {
        headers: { 'Content-Type': 'application/json' },
    });

    // 주문 30번
    for (let i = 0; i < 30; i++) { // (i % 4) + 1 = productId
        let productId = (i % 4) + 1;
        let quantity = Math.floor(Math.random() * 5) + 1
        http.post(`${BASE_URL}/orders`, JSON.stringify(productOrder(userKey, productId, quantity)), {
            headers: { 'Content-Type': 'application/json' },
        });
    }


}



export default function () {

    http.get(`${BASE_URL}/orders/recommend/RECOMMEND_01`, {
        headers: { 'Content-Type': 'application/json' },
    });

    sleep(0.5);
}