package com.springexercise.alerter.service;

import com.springexercise.alerter.controller.AreaAlertResponse;
import com.springexercise.alerter.controller.CityAlertResponse;
import com.springexercise.alerter.model.Area;
import com.springexercise.common.model.Severity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AreaAlertHandlerImpl implements AreaAlertHandler {

    private final CityAlertHandler cityAlertHandler;

    @Autowired
    public AreaAlertHandlerImpl(CityAlertHandler cityAlertHandler) {
        this.cityAlertHandler = cityAlertHandler;
    }

    @Override
    public AreaAlertResponse handleAreaAlert(Area area, Severity severity) {
        log.info("Working on area {}", area.getAreaName());
        AreaAlertResponse.AreaAlertResponseBuilder builder = AreaAlertResponse.builder();
        builder.areaName(area.getAreaName());
        builder.wasFound(true);
        List<String> citiesInArea = area.getCitiesInArea();
        for (String cityName : citiesInArea) {
            CityAlertResponse response = cityAlertHandler.alertWholeCity(cityName, severity);
            builder.cityAlertResponse(response);
        }
        return builder.build();
    }
}
