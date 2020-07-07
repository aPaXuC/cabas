package com.springexercise.alerter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Citizen {
    @Id
    private int citizenID;
    private String firstName;
    private String lastName;
    private String city;
    private int phone;
    private int guardianID;
}
