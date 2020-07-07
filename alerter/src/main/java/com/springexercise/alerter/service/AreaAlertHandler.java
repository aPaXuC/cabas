package com.springexercise.alerter.service;

import com.springexercise.alerter.controller.AreaAlertResponse;
import com.springexercise.alerter.model.Area;
import com.springexercise.common.model.Severity;

public interface AreaAlertHandler {
    AreaAlertResponse handleAreaAlert(Area area, Severity severity);
}
