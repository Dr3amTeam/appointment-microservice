package com.dreamteam.appointmentmicroservice.query.projections;

import com.dreamteam.appointmentmicroservice.command.domain.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.Instant;
@Entity
public class AppointmentView {
    @Id @Column(length = 36) @Getter @Setter
    @Schema(example = "a77e07f9-b660-4400-a70b-424b0915f7bb", description = "ID de la cita")
    private String appointmentId;
    @Column(length = 36) @Getter @Setter
    @Schema(example = "b5765205-6c57-4d32-8ad2-d62b25e413f3", description = "ID del pago")
    private String paymentId;
    @Column(length = 20) @Getter @Setter
    @Schema(example = "20/12/2021", description = "Fecha de la cita")
    private String date;
    @Column(length = 50) @Getter @Setter
    @Schema(example = "Cita acordada con exito", description = "Descripción de la cita")
    private String description;
    @Column(length = 20) @Getter @Setter
    @Schema(example = "ACCOMPLISHED", description = "Estado de la cita")
    private String status;
    @Schema(example = "2021-11-19T09:15:52.390Z", description = "Fecha de la creación")
    private Instant createAt;
    @Column(nullable = true)@Getter @Setter
    @Schema(example = "2021-12-19T09:15:52.390Z", description = "Fecha de la actualización")
    private Instant updateAt;

    public AppointmentView() {
    }

    public AppointmentView(String appointmentId, String paymentId, String date, String description, String status,
                           Instant createAt, Instant updateAt) {
        this.appointmentId = appointmentId;
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
