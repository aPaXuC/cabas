package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;
import com.springexercise.notificator.model.MessagesLog;
import com.springexercise.notificator.repositories.MessagesLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MessageLogUpdaterImpl implements MessageLogUpdater {
    private final MessagesLogRepository messagesLogRepository;
    private final MessageBuilder messageBuilder;

    @Autowired
    public MessageLogUpdaterImpl(MessagesLogRepository messagesLogRepository, MessageBuilder messageBuilder) {
        this.messagesLogRepository = messagesLogRepository;
        this.messageBuilder = messageBuilder;
    }

    @Override
    public void addNotificationToLog(CitizenAlertMessage citizenAlertMessage) {
        MessagesLog messagesLog = MessagesLog.builder()
                .firstName(citizenAlertMessage.getFirstName())
                .lastName(citizenAlertMessage.getLastName())
                .phone(citizenAlertMessage.getPhone())
                .messageContent(messageBuilder.buildMessage(citizenAlertMessage))
                .severity(citizenAlertMessage.getSeverity())
                .notificationTime(LocalDateTime.now())
                .build();
        log.info("Saving to DB: {}", messagesLog);
        messagesLogRepository.save(messagesLog);
    }
}
