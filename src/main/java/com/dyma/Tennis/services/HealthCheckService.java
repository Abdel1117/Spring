package com.dyma.Tennis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyma.Tennis.repository.HealthCheckRepository;
import com.dyma.Tennis.web.ApplicationStatus;
import com.dyma.Tennis.web.HealthCheck;

@Service
public class HealthCheckService {

    @Autowired
    private HealthCheckRepository healthCheckRepository;

    public HealthCheck HealthCheck() {

        Long applicationConnections = healthCheckRepository.countApplicationConnections();
        System.out.println(applicationConnections);
        if (applicationConnections > 0) {
            return new HealthCheck(ApplicationStatus.OK, "Welcome to Dyma Tennis");
        } else {
            return new HealthCheck(ApplicationStatus.KO,
                    "Dyma Tennis is not fully functional, please check your configuration.");
        }
    }
}
