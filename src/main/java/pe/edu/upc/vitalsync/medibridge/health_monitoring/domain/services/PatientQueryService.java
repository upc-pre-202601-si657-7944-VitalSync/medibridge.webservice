package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services;

import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    List<Patient> getAll();
    Optional<Patient> getById(Long id);
}