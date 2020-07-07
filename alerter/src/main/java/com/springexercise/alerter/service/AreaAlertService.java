package com.springexercise.alerter.service;


import com.springexercise.alerter.controller.AreaAlertResponse;
import com.springexercise.alerter.controller.AreaAlertResponseToBadRequest;
import com.springexercise.common.model.Severity;

import java.util.List;

public interface AreaAlertService {
    List<AreaAlertResponse> alertAllCitizens(List<String> areaNames, Severity severity);
    AreaAlertResponseToBadRequest getBadRequestResponse();
}
