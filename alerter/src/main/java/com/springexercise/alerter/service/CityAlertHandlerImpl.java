package com.springexercise.alerter.service;

import com.springexercise.alerter.configuration.AmqpProperties;
import com.springexercise.alerter.controller.CityAlertResponse;
import com.springexercise.alerter.model.Citizen;
import com.springexercise.alerter.repositories.CitizensRepository;
import com.springexercise.common.model.CitizenAlertMessage;
import com.springexercise.common.model.Severity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class CityAlertHandlerImpl implements CityAlertHandler {
    private final CitizensRepository citizensRepository;
    private final RabbitTemplate rabbitTemplate;
    private final AmqpProperties amqpProperties;

    @Autowired
    public CityAlertHandlerImpl(CitizensRepository citizensRepository, RabbitTemplate rabbitTemplate, AmqpProperties amqpProperties) {
        this.citizensRepository = citizensRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.amqpProperties = amqpProperties;
    }

    @Override
    public CityAlertResponse alertWholeCity(String cityName, Severity severity) {
        log.info("Working on city {}", cityName);
        List<Citizen> allByCity = citizensRepository.findAllByCity(cityName);
        AtomicInteger notFound = new AtomicInteger(0);
        allByCity.forEach(citizen -> sendToCitizen(citizen, severity, notFound));
        return new CityAlertResponse(cityName, allByCity.size(), notFound.get());
    }

    private void sendToCitizen(Citizen citizen, Severity severity, AtomicInteger notFound) {
        sendAlertMessage(citizen.getFirstName(), citizen.getLastName(), citizen.getPhone(), severity);
        if (citizen.getGuardianID() > 0) {
            sendToGuardian(citizen, citizen.getGuardianID(), severity, notFound);
        }
    }

    private void sendToGuardian(Citizen citizen, Integer guardianID, Severity severity, AtomicInteger notFound) {
        Optional<Citizen> optionalGuardian = citizensRepository.findByCitizenID(guardianID);
        if (optionalGuardian.isPresent()) {
            Citizen guardian = optionalGuardian.get();
            log.info("Sending also to guardian {}", guardian.getCitizenID());
            sendAlertMessage(citizen.getFirstName(), citizen.getLastName(), guardian.getPhone(), severity);
        } else {
            log.error("Couldn't find guardian {}" , guardianID);
            notFound.getAndIncrement();
        }
    }

    private void sendAlertMessage(String firstName, String lastName, int phone, Severity severity) {
        CitizenAlertMessage alertMessage = CitizenAlertMessage.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .severity(severity)
                .build();
        rabbitTemplate.convertAndSend(amqpProperties.getExchange(), amqpProperties.getQueue(), alertMessage);
    }
}
