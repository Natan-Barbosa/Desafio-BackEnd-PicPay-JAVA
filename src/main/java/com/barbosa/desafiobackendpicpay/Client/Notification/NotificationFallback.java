package com.barbosa.desafiobackendpicpay.Client.Notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class NotificationFallback implements NotificationClient {
    private static final Logger log = LogManager.getLogger(NotificationFallback.class);

    @Override
    public void sendNotification() {
        log.info("Notification Service If Offline");
    }
}
