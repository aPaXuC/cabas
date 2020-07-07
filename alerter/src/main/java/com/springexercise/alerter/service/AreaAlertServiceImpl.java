package com.springexercise.alerter.service;

import com.springexercise.alerter.controller.AreaAlertResponse;
import com.springexercise.alerter.controller.AreaAlertResponseToBadRequest;
import com.springexercise.alerter.model.Area;
import com.springexercise.alerter.repositories.AreasRepository;
import com.springexercise.common.model.Severity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AreaAlertServiceImpl implements AreaAlertService {

    private final AreasRepository areasRepository;
    private final AreaAlertHandler areaAlertHandler;

    @Autowired
    public AreaAlertServiceImpl(AreasRepository areasRepository, AreaAlertHandler areaAlertHandler) {
        this.areasRepository = areasRepository;
        this.areaAlertHandler = areaAlertHandler;
    }

    @Override
    public List<AreaAlertResponse> alertAllCitizens(List<String> areaNames, Severity severity) {
        List<AreaAlertResponse> allResponses = new ArrayList<>();
        log.info("Number of received areas: {}", areaNames.size());
        areaNames.forEach(areaName -> {
            Optional<Area> optionalArea = areasRepository.findByAreaName(areaName);
            if (optionalArea.isPresent()) {
                AreaAlertResponse response = areaAlertHandler.handleAreaAlert(optionalArea.get(), severity);
                allResponses.add(response);
            } else {
                log.error(areaName + " area wasn't found.");
                AreaAlertResponse response = AreaAlertResponse.builder()
                        .areaName(areaName)
                        .wasFound(false)
                        .build();
                allResponses.add(response);
            }
        });
        return allResponses;
    }

    @Override
    public AreaAlertResponseToBadRequest getBadRequestResponse() {
        return AreaAlertResponseToBadRequest.builder()
                ._info("Pick 1 of possible severities and 1 or more of possible areas")
                .severities(Severity.values())
                .areasNames(areasRepository.findAll().stream().map(Area::getAreaName).collect(Collectors.toList()))
                .build();
    }
}
