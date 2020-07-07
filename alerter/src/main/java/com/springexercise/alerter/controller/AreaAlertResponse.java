package com.springexercise.alerter.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class AreaAlertResponse {
    private String areaName;
    private boolean wasFound;
    @Singular
    private List<CityAlertResponse> cityAlertResponses;
}
