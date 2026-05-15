package pe.edu.upc.vitalsync.medibridge.iam.infrastructure.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.Role;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.User;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.entities.RoleName;
import pe.edu.upc.vitalsync.medibridge.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.upc.vitalsync.medibridge.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        var adminRole = roleRepo.findByName(RoleName.ADMIN).orElseGet(() -> roleRepo.save(new Role(RoleName.ADMIN)));
        var doctorRole = roleRepo.findByName(RoleName.DOCTOR).orElseGet(() -> roleRepo.save(new Role(RoleName.DOCTOR)));
        var patientRole = roleRepo.findByName(RoleName.PATIENT).orElseGet(() -> roleRepo.save(new Role(RoleName.PATIENT)));

        if (userRepo.findByEmail("admin@gmail.com").isEmpty()) {
            var u = new User("Admin", "Root", "admin@gmail.com", encoder.encode("admin123"));
            u.setRoles(Set.of(adminRole));
            userRepo.save(u);
        }
        if (userRepo.findByEmail("doctor@gmail.com").isEmpty()) {
            var u = new User("Ana", "Soto", "doctor@gmail.com", encoder.encode("doctor123"));
            u.setRoles(Set.of(doctorRole));
            userRepo.save(u);
        }
        if (userRepo.findByEmail("patient@gmail.com").isEmpty()) {
            var u = new User("Juan", "Gonzales", "patient@gmail.com", encoder.encode("patient123"));
            u.setRoles(Set.of(patientRole));
            userRepo.save(u);
        }
    }
}
