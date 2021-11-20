package com.dreamteam.appointmentmicroservice.command.application.dto;

import lombok.Value;

@Value
public class EditAppointmentResponseDto {
    private String appointmentId;
    private String paymentId;
    private String date;
    private String description;
}
