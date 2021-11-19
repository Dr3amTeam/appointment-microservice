package com.dreamteam.appointmentmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dreamteam.appointmentmicroservice.command.application.dto.AppointmentRequestDto;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDate;
import com.dreamteam.appointmentmicroservice.command.infra.AppointmentDateRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateAppointmentValidator {

    public final AppointmentDateRepository appointmentDateRepository;

    public CreateAppointmentValidator(AppointmentDateRepository appointmentDateRepository) {
        this.appointmentDateRepository = appointmentDateRepository;
    }

    public Notification validate(AppointmentRequestDto appointmentRequestDto){
        Notification notification1 = new Notification();
        String paymentId = appointmentRequestDto.getPaymentId().trim();
        if (paymentId.isEmpty()){
            notification1.addError("Appointment Payment is required");
        }
        String date = appointmentRequestDto.getDate().trim();
        if (date.isEmpty()){
            notification1.addError("Appointment date is required");
        }
        String description = appointmentRequestDto.getDescription().trim();
        if (description.isEmpty()) {
            notification1.addError("Appointment description is required");
        }
        Optional<AppointmentDate> appointmentOptional = appointmentDateRepository.findById(date);
        if (appointmentOptional.isPresent()){
            notification1.addError("Employee must have only 1 appointment at a time");
        }
        return notification1;
    }
}
