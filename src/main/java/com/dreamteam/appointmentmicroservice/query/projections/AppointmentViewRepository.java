package com.dreamteam.appointmentmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentViewRepository extends JpaRepository<AppointmentView,String> {
    List<AppointmentView> getAppointmentViewsByStatus(String status);
    Optional<AppointmentView> getAppointmentViewByAppointmentId(String appointmentId);
}
