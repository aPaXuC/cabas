package com.springexercise.alerter.controller;

import com.springexercise.common.model.Severity;
import lombok.Data;

import java.util.List;

@Data
public class AreaAlertRequest {
    private final List<String> areas;
    private final Severity severity;
}
