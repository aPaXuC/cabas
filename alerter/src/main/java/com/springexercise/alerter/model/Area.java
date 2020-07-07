package com.springexercise.alerter.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Area {
    @Id
    private String areaName;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> citiesInArea;
}
