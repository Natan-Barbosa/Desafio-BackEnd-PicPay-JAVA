package com.barbosa.desafiobackendpicpay.Services.NotificationService;

import com.barbosa.desafiobackendpicpay.Client.Notification.NotificationClient;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    private static final Logger log = LogManager.getLogger(NotificationService.class);
    @Autowired
    private NotificationClient notificationClient;

    public void sendNotification() {
        try {
            notificationClient.sendNotification();
        } catch (FeignException.GatewayTimeout e) {
            log.error(e.getMessage());
        } catch (FeignException e) {
            log.error(e.getMessage());
        }
    }
}
