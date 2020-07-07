package com.springexercise.notificator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationServiceUsingLogger implements NotificationService {
    @Override
    public void sendNotification(int phone, String message) {
        log.info("Sending to '{}' message '{}'", phone, message);
    }
}
