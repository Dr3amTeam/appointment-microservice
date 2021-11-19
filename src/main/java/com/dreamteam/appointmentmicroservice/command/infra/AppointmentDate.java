package com.dreamteam.appointmentmicroservice.command.infra;

import com.dreamteam.appointmentmicroservice.command.domain.AppointmentStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AppointmentDate {
    @Id
    private String appointmentId;
    @NotNull
    private String paymentId;
    @NotNull
    private String date;
    @NotNull
    private String description;
    @NotNull
    private AppointmentStatus status;

    public AppointmentDate(){}

    public AppointmentDate(String appointmentId, String paymentId, String date, String description, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.paymentId = paymentId;
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
