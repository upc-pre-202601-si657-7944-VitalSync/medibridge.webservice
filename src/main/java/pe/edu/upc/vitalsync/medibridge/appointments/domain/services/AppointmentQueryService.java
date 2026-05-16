package pe.edu.upc.vitalsync.medibridge.appointments.domain.services;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    List<Appointment> getAll();
    Optional<Appointment> getById(Long id);
}