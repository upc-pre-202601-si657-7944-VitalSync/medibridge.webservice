package pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByCmpCode(String cmpCode);
}