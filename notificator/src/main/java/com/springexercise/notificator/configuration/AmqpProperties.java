package com.springexercise.notificator.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("amqp")
public class AmqpProperties {
    private String queue;
    private String exchange;
    private int concurrentConsumers;
}
