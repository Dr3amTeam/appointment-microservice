package com.dreamteam.appointmentmicroservice.command.application.dto;


import lombok.Value;

@Value
public class AppointmentRequestDto {
    private String paymentId;
    private String date;
    private String description;

}
