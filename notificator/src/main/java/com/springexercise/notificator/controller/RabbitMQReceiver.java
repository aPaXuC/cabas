package com.springexercise.notificator.controller;

import com.springexercise.common.model.CitizenAlertMessage;
import com.springexercise.notificator.service.NotificationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReceiver implements Receiver {

    private final NotificationHandler notificationHandler;

    @Autowired
    public RabbitMQReceiver(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void receiveMessage(CitizenAlertMessage citizenAlertMessage) {
        log.info("New notification request was received.");
        notificationHandler.notifyCitizen(citizenAlertMessage);
    }
}
