package com.dreamteam.appointmentmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentDateRepository extends JpaRepository<AppointmentDate, String> {
    Optional<AppointmentDate> getAppointmentDateByAppointmentId(String date);
}
