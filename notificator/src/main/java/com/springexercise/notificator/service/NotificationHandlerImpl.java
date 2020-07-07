package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationHandlerImpl implements NotificationHandler {
    private final MessageLogUpdater logUpdater;
    private final NotificationService notificationService;
    private final MessageBuilder messageBuilder;

    @Autowired
    public NotificationHandlerImpl(MessageLogUpdater logUpdater, NotificationService notificationService, MessageBuilder messageBuilder) {
        this.logUpdater = logUpdater;
        this.notificationService = notificationService;
        this.messageBuilder = messageBuilder;
    }

    @Override
    public void notifyCitizen(CitizenAlertMessage alertMessage) {
        notificationService.sendNotification(alertMessage.getPhone(), messageBuilder.buildMessage(alertMessage));
        logUpdater.addNotificationToLog(alertMessage);
    }
}
