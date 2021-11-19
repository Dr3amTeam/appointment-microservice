package com.dreamteam.appointmentmicroservice.command.application.dto;

import lombok.Value;

@Value
public class AppointmentResponseDto {
    private String date;
    private String description;

}
