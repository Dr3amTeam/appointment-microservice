package com.dreamteam.appointmentmicroservice.command.domain;

import com.dhome.appointmentmicroservice.commands.CreateAppointment;
import com.dhome.appointmentmicroservice.commands.EditAppointment;
import com.dhome.appointmentmicroservice.commands.MarkAppointmentAsCompleted;
import com.dhome.appointmentmicroservice.commands.MarkAppointmentAsFailed;
import com.dhome.appointmentmicroservice.events.AppointmentCompleted;
import com.dhome.appointmentmicroservice.events.AppointmentCreated;
import com.dhome.appointmentmicroservice.events.AppointmentEdited;
import com.dhome.appointmentmicroservice.events.AppointmentFailed;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Appointment {
    @AggregateIdentifier
    private String appointmentId;
    private String paymentId;
    private String date;
    private String description;
    private AppointmentStatus status;

    public Appointment(){

    }
    @CommandHandler
    public Appointment(CreateAppointment command){
        Instant now = Instant.now();
        apply(new AppointmentCreated(
                command.getAppointmentId(),
                command.getPaymentId(),
                command.getDate(),
                command.getDescription(),
                now

        ));

    }

    @CommandHandler
    public void handle(EditAppointment command){
        Instant now = Instant.now();
        apply(new AppointmentEdited(
                command.getAppointmentId(),
                command.getPaymentId(),
                command.getDate(),
                command.getDescription(),
                now

        ));

    }

    @CommandHandler
    public void handle(MarkAppointmentAsCompleted command){
        Instant now = Instant.now();
        apply(new AppointmentCompleted(command.getAppointmentId(),now));
    }

    @CommandHandler
    public void handle(MarkAppointmentAsFailed command){
        Instant now = Instant.now();
        apply(new AppointmentFailed(command.getAppointmentId(),now));
    }

    @EventSourcingHandler
    public void on(AppointmentCreated event){
        this.appointmentId=event.getAppointmentId();
        this.date= event.getDate();
        this.description= event.getDescription();
        this.status=AppointmentStatus.PENDING;
    }

    @EventSourcingHandler
    public void on(AppointmentEdited event){
        appointmentId=event.getAppointmentId();
        paymentId = event.getPaymentId();
        date= event.getDate();
        description=event.getDescription();
    }

    @EventSourcingHandler
    public void on(AppointmentCompleted event){
        this.status=AppointmentStatus.COMPLETED;
    }


    @EventSourcingHandler
    public void on(AppointmentFailed event){
        this.status=AppointmentStatus.FAILED;
    }
}
