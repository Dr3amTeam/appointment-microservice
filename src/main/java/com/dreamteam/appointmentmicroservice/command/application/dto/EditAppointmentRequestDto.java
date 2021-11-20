package com.dreamteam.appointmentmicroservice.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


public class EditAppointmentRequestDto {
    @Schema(example = "a77e07f9-b660-4400-a70b-424b0915f7bb", description = "ID de la cita")
    private @Setter @Getter
    String appointmentId;
    @Schema(example = "b5765205-6c57-4d32-8ad2-d62b25e413f3", description = "ID del pago")
    private @Getter String paymentId;
    @Schema(example = "20/12/2021", description = "Fecha de la cita")
    private @Getter String date;
    @Schema(example = "Cita acordada con exito", description = "Descripción de la cita")
    private @Getter String description;
}
