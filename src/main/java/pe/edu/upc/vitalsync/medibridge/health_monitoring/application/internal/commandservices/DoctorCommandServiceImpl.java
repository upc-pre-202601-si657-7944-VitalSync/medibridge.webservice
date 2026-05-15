package pe.edu.upc.vitalsync.medibridge.health_monitoring.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreateDoctorCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeleteDoctorCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdateDoctorCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services.DoctorCommandService;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories.DoctorRepository;


@Service @Transactional @RequiredArgsConstructor
public class DoctorCommandServiceImpl implements DoctorCommandService {
    private final DoctorRepository repo;

    @Override public Doctor create(CreateDoctorCommand c) {
        repo.findByEmail(c.email()).ifPresent(d -> { throw new IllegalArgumentException("Email already in use"); });
        repo.findByCmpCode(c.cmpCode()).ifPresent(d -> { throw new IllegalArgumentException("CMP already in use"); });
        return repo.save(new Doctor(c.firstName(), c.lastName(), c.email(), c.specialization(),
                c.cmpCode(), c.studyCentre(), c.phoneNumber()));
    }

    @Override public Doctor update(UpdateDoctorCommand c) {
        var d = repo.findById(c.id()).orElseThrow(() -> new IllegalArgumentException("Doctor not found: "+c.id()));
        if (c.firstName()!=null) d.setFirstName(c.firstName());
        if (c.lastName()!=null)  d.setLastName(c.lastName());
        if (c.email()!=null)     d.setEmail(c.email());
        if (c.specialization()!=null) d.setSpecialization(c.specialization());
        if (c.cmpCode()!=null)   d.setCmpCode(c.cmpCode());
        if (c.studyCentre()!=null) d.setStudyCentre(c.studyCentre());
        if (c.phoneNumber()!=null) d.setPhoneNumber(c.phoneNumber());
        return repo.save(d);
    }

    @Override public void delete(DeleteDoctorCommand c) {
        if (!repo.existsById(c.id())) throw new IllegalArgumentException("Doctor not found: "+c.id());
        repo.deleteById(c.id());
    }
}
