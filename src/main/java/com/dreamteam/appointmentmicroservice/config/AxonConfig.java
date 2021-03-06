package com.dreamteam.appointmentmicroservice.config;
import com.dreamteam.appointmentmicroservice.command.domain.Appointment;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {
    @Bean
    public Repository<Appointment> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Appointment.class)
                .eventStore(eventStore)
                .build();
    }
}
