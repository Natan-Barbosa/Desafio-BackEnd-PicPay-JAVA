package com.barbosa.desafiobackendpicpay.Services.NotificationService;

import com.barbosa.desafiobackendpicpay.Client.Notification.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @Autowired
    private NotificationClient notificationClient;

    public void sendNotification(){
        notificationClient.sendNotification();
    }
}
