package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorQueryService {
    List<Doctor> getAll();
    Optional<Doctor> getById(Long id);
}
