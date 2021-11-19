package com.dreamteam.appointmentmicroservice.command.application.handlers;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEventHandler {
    @EventHandler
    public void on(Object event){
        System.out.println("Event received: " + event.toString());
    }
}
