package com.springexercise.common.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CitizenAlertMessage implements Serializable {
    private String firstName;
    private String lastName;
    private int phone;
    private Severity severity;
}
