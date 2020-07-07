package com.springexercise.alerter.controller;

import com.springexercise.alerter.service.AreaAlertService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alert")
public class AreaAlertController {

    private final AreaAlertService areaAlertService;

    @Autowired
    public AreaAlertController(AreaAlertService areaAlertService) {
        this.areaAlertService = areaAlertService;
    }

    @PostMapping("/initAreaAlert")
    @ApiOperation(value = "Init Area Alert", notes = "Produce relevant alert for all citizens in given area.", response = AreaAlertResponse.class)
    public List<AreaAlertResponse> initAreaAlert(@NonNull @RequestBody AreaAlertRequest areaAlertRequest) {
        log.info("New alert request was received. {}", areaAlertRequest);
        return areaAlertService.alertAllCitizens(areaAlertRequest.getAreas(), areaAlertRequest.getSeverity());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public AreaAlertResponseToBadRequest handleBadRequest() {
        log.error("Handling Bad Request exception.");
        return areaAlertService.getBadRequestResponse();
    }
}
