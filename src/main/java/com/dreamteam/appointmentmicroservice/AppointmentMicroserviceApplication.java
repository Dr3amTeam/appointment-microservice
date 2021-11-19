package com.dreamteam.appointmentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppointmentMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentMicroserviceApplication.class, args);
    }

}
