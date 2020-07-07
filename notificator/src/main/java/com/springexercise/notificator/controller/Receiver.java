package com.springexercise.notificator.controller;


import com.springexercise.common.model.CitizenAlertMessage;

public interface Receiver {

    public void receiveMessage(CitizenAlertMessage citizenAlertMessage);

}
