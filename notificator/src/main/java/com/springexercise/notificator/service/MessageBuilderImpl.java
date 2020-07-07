package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;
import com.springexercise.notificator.model.Message;
import com.springexercise.notificator.repositories.MessagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class MessageBuilderImpl implements MessageBuilder {
    private final MessagesRepository messagesRepository;

    @Autowired
    public MessageBuilderImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public String buildMessage(CitizenAlertMessage citizenAlertMessage) {
        Optional<Message> messageOptional = messagesRepository.findById(citizenAlertMessage.getSeverity());
        if (! messageOptional.isPresent()) {
            log.error("Couldn't find message with severity {}", citizenAlertMessage.getSeverity());
        }
        String name = citizenAlertMessage.getLastName() + " " + citizenAlertMessage.getFirstName();
        String message = String.format(messageOptional.get().getContent(), name);
        return message;
    }
}
