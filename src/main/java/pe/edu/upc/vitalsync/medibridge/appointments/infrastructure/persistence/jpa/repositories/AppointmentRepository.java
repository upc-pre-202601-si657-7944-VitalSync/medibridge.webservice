package pe.edu.upc.vitalsync.medibridge.appointments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}