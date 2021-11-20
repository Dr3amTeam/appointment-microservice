package com.dreamteam.appointmentmicroservice.command.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class AppointmentRequestDto {
    @Schema(example = "b5765205-6c57-4d32-8ad2-d62b25e413f3", description = "ID del pago")
    private String paymentId;
    @Schema(example = "20/12/2021", description = "Fecha de la cita")
    private String date;
    @Schema(example = "Cita acordada con exito", description = "Descripción de la cita")
    private String description;

}
