package com.dreamteam.appointmentmicroservice.command.application.handlers;

import com.dhome.appointmentmicroservice.events.AppointmentEdited;
import com.dreamteam.appointmentmicroservice.command.domain.AppointmentStatus;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDate;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDateRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("appointment")
public class AppointmentEventHandler {

    private final AppointmentDateRepository appointmentDateRepository;

    public AppointmentEventHandler(AppointmentDateRepository appointmentDateRepository) {
        this.appointmentDateRepository = appointmentDateRepository;
    }

    @EventHandler
    public void on(AppointmentEdited event){
        Optional<AppointmentDate> appointmentDateOptional =
                appointmentDateRepository.getAppointmentDateByAppointmentId(event.getAppointmentId());
        appointmentDateOptional.ifPresent(appointmentDateRepository::delete);
        appointmentDateRepository.save(new AppointmentDate(
            event.getAppointmentId(),
                event.getPaymentId(),
                event.getDate(),
                event.getDescription()
        ));
    }
}
