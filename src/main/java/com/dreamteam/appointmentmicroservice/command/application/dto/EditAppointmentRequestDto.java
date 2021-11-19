package com.dreamteam.appointmentmicroservice.command.application.dto;

import lombok.Getter;
import lombok.Setter;


public class EditAppointmentRequestDto {
    private @Setter @Getter
    String appointmentId;
    private @Getter String paymentId;
    private @Getter String date;
    private @Getter String description;
}
