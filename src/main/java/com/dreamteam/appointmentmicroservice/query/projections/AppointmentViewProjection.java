package com.dreamteam.appointmentmicroservice.query.projections;

import com.dhome.appointmentmicroservice.events.AppointmentCompleted;
import com.dhome.appointmentmicroservice.events.AppointmentCreated;
import com.dhome.appointmentmicroservice.events.AppointmentEdited;
import com.dhome.appointmentmicroservice.events.AppointmentFailed;
import com.dreamteam.appointmentmicroservice.command.domain.AppointmentStatus;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class AppointmentViewProjection {
    private final AppointmentViewRepository appointmentViewRepository;

    public AppointmentViewProjection(AppointmentViewRepository appointmentViewRepository) {
        this.appointmentViewRepository = appointmentViewRepository;
    }
    @EventHandler
    public void on(AppointmentEdited event, @Timestamp Instant timestamp){
        Optional<AppointmentView> optionalAppointmentView =
                appointmentViewRepository.getAppointmentViewByAppointmentId(event.getAppointmentId());
        if (optionalAppointmentView.isPresent()){
            AppointmentView appointmentView = optionalAppointmentView.get();
            appointmentView.setPaymentId(event.getPaymentId());
            appointmentView.setDate(event.getDate());
            appointmentView.setDescription(event.getDescription());
            appointmentView.setStatus(AppointmentStatus.ACCOMPLISHED.toString());
            appointmentViewRepository.save(appointmentView);
        }
    }
    @EventHandler
    public void on(AppointmentCreated event, @Timestamp Instant timestamp){
        String appointmentId =event.getAppointmentId();
        String paymentId = event.getPaymentId();
        String date = event.getDate();
        String description = event.getDescription();
        String status = AppointmentStatus.PENDING.toString();

        AppointmentView appointmentView = new AppointmentView(appointmentId,paymentId,date,description,
                status,event.getOccurredOn(),timestamp);
        appointmentViewRepository.save(appointmentView);
    }
    @EventHandler
    public void on(AppointmentFailed event) {
        Optional<AppointmentView> appointmentViewOptional = appointmentViewRepository.findById(event.getAppointmentId());
        if (appointmentViewOptional.isPresent()) {
            AppointmentView appointmentView = appointmentViewOptional.get();
            appointmentView.setStatus(AppointmentStatus.FAILED.toString());
            appointmentView.setUpdateAt(event.getOccurredOn());
            appointmentViewRepository.save(appointmentView);
        }
    }

    @EventHandler
    public void on(AppointmentCompleted event) {
        Optional<AppointmentView> appointmentViewOptional = appointmentViewRepository.findById(event.getAppointmentId());
        if (appointmentViewOptional.isPresent()) {
            AppointmentView appointmentView = appointmentViewOptional.get();
            appointmentView.setStatus(AppointmentStatus.COMPLETED.toString());
            appointmentView.setUpdateAt(event.getOccurredOn());
            appointmentViewRepository.save(appointmentView);
        }
    }
}
