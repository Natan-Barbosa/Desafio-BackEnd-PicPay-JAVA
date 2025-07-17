package com.barbosa.desafiobackendpicpay.Client.Notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "https://util.devi.tools/api/v1/notify", name = "notificationClient")
public interface NotificationClient {

    @PostMapping
    void sendNotification();
}
