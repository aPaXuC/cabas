package com.springexercise.alerter.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityAlertResponse {
    private String cityName;
    private int totalCitizens;
    private int notFoundCitizens;
}
