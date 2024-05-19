package com.hhplus.commerce.app.order.event;

import com.hhplus.commerce.app.order.service.DataPlatformSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final DataPlatformSender dataPlatformSender;


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendDataPlatform(OrderEvent event) {
        log.info("event 실행");
        dataPlatformSender.send(event.request());
    }
}
