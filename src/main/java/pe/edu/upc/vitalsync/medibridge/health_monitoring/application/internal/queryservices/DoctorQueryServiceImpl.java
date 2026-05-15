package pe.edu.upc.vitalsync.medibridge.health_monitoring.application.internal.queryservices;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services.DoctorQueryService;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service @Transactional(readOnly = true) @RequiredArgsConstructor
public class DoctorQueryServiceImpl implements DoctorQueryService {
    private final DoctorRepository repo;
    @Override public List<Doctor> getAll(){ return repo.findAll(); }
    @Override public Optional<Doctor> getById(Long id){ return repo.findById(id); }
}
