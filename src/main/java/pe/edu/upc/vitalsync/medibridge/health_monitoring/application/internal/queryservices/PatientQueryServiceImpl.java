package pe.edu.upc.vitalsync.medibridge.health_monitoring.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services.PatientQueryService;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service @Transactional(readOnly = true) @RequiredArgsConstructor
public class PatientQueryServiceImpl implements PatientQueryService {
    private final PatientRepository repo;
    @Override public List<Patient> getAll(){ return repo.findAll(); }
    @Override public Optional<Patient> getById(Long id){ return repo.findById(id); }
}
