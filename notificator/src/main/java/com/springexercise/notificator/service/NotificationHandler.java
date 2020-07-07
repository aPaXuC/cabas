package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;

public interface NotificationHandler {
    void notifyCitizen(CitizenAlertMessage alertMessage);
}
