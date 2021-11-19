package com.dreamteam.appointmentmicroservice.command.application.dto;

public class AppointmentErrorDto {
    private String message;
    public AppointmentErrorDto(){
        this.message = "Error with the appointment´s creation";
    }
    public AppointmentErrorDto(String message) {
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
