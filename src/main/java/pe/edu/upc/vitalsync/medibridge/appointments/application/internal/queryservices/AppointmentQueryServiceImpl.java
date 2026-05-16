package pe.edu.upc.vitalsync.medibridge.appointments.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.services.AppointmentQueryService;
import pe.edu.upc.vitalsync.medibridge.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;
F
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getById(Long id) {
        return appointmentRepository.findById(id);
    }
}