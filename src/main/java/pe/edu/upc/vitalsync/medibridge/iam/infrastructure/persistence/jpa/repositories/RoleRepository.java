package pe.edu.upc.vitalsync.medibridge.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.Role;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.entities.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
