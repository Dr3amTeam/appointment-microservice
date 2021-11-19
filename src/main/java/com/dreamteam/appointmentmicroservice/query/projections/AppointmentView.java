package com.dreamteam.appointmentmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.Instant;
@Entity
public class AppointmentView {
    @Id @Column(length = 36) @Getter @Setter
    private String appointmentId;
    @Column(length = 36) @Getter @Setter
    private String paymentId;
    @Column(length = 20) @Getter @Setter
    private String date;
    @Column(length = 50) @Getter @Setter
    private String description;
    @Column() @Getter @Setter
    private String appointmentStatus;
    private Instant createAt;
    @Column(nullable = true)@Getter @Setter
    private Instant updateAt;

    public AppointmentView() {
    }

    public AppointmentView(String appointmentId, String paymentId, String date, String description, String appointmentStatus, Instant createAt, Instant updateAt) {
        this.appointmentId = appointmentId;
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.appointmentStatus = appointmentStatus;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
