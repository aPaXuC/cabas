package com.springexercise.notificator.service;

import com.springexercise.common.model.CitizenAlertMessage;

public interface MessageBuilder {
    String buildMessage(CitizenAlertMessage citizenAlertMessage);
}
