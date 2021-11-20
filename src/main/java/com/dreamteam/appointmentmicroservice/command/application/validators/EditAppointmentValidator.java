package com.dreamteam.appointmentmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.appointmentmicroservice.command.application.dto.EditAppointmentRequestDto;
import com.dreamteam.appointmentmicroservice.command.domain.Appointment;

import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDateRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class EditAppointmentValidator {
    private final Repository<Appointment> appointmentRepository;
    private final AppointmentDateRepository appointmentDateRepository;

    public EditAppointmentValidator(Repository<Appointment> appointmentRepository, AppointmentDateRepository appointmentDateRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDateRepository = appointmentDateRepository;
    }

    public Notification validate(EditAppointmentRequestDto editAppointmentRequestDto){
        Notification notification2= new Notification();
        String appointmentId = editAppointmentRequestDto.getAppointmentId().trim();
        if(appointmentId.isEmpty()){
            notification2.addError("Appointment Id is required");
        }
        String paymentId = editAppointmentRequestDto.getPaymentId().trim();
        if (paymentId.isEmpty()){
            notification2.addError("Appointment payment is required");
        }
        String Date = editAppointmentRequestDto.getDate().trim();
        if (Date.isEmpty()){
            notification2.addError("Appointment Date is required");
        }
        String description = editAppointmentRequestDto.getDescription().trim();
        if (description.isEmpty()){
            notification2.addError("Appointment Description is required");
        }
        return notification2;
    }
}
