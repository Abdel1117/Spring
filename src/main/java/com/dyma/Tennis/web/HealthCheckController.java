package com.dyma.Tennis.web;

import org.springframework.web.bind.annotation.RestController;

import com.dyma.Tennis.services.HealthCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    @GetMapping("healthcheck")
    public HealthCheck HealthCheck() {
        return healthCheckService.HealthCheck();
    }
}
