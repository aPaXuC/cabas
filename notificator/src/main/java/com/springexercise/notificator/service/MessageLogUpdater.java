package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;

public interface MessageLogUpdater {
    void addNotificationToLog(CitizenAlertMessage citizenAlertMessage);
}
