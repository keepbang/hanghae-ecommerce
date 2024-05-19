package com.hhplus.commerce.app.order.event;

import com.hhplus.commerce.app.order.dto.OrderRequest;

public record OrderEvent(
    OrderRequest request
) {

}
