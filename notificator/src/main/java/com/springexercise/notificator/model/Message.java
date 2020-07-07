package com.springexercise.notificator.model;

import com.springexercise.common.model.Severity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Message {
    @Id
    private Severity severity;
    private String content;
}
