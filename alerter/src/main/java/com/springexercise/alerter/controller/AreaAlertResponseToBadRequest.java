package com.springexercise.alerter.controller;

import com.springexercise.common.model.Severity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AreaAlertResponseToBadRequest {
    private String _info;
    private Severity[] severities;
    private List<String> areasNames;
}
