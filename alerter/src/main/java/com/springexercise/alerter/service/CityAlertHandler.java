package com.springexercise.alerter.service;

import com.springexercise.alerter.controller.CityAlertResponse;
import com.springexercise.common.model.Severity;

public interface CityAlertHandler {
    CityAlertResponse alertWholeCity(String cityName, Severity severity);
}
