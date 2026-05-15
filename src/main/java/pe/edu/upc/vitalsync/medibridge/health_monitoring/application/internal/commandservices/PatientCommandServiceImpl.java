package pe.edu.upc.vitalsync.medibridge.health_monitoring.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.AssignDoctorToPatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreatePatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeletePatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdatePatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services.PatientCommandService;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories.DoctorRepository;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infraestructure.persistence.jpa.repositories.PatientRepository;

@Service @Transactional @RequiredArgsConstructor
public class PatientCommandServiceImpl implements PatientCommandService {
    private final PatientRepository repo;
    private final DoctorRepository doctorRepo;

    @Override public Patient create(CreatePatientCommand c) {
        repo.findByEmail(c.email()).ifPresent(p -> { throw new IllegalArgumentException("Email already in use"); });
        repo.findByPatientId(c.patientId()).ifPresent(p -> { throw new IllegalArgumentException("PatientId already in use"); });
        if (c.assignedDoctorId()!=null && !doctorRepo.existsById(c.assignedDoctorId()))
            throw new IllegalArgumentException("Assigned doctor not found: " + c.assignedDoctorId());

        var p = new Patient(c.firstName(), c.lastName(), c.email(), c.age(), c.sex(), c.bloodType(),
                c.patientId(), c.healthInsurance(), c.preferredHospital(), c.status(),
                c.assignedDoctorId(), c.registerDate());
        return repo.save(p);
    }

    @Override public Patient update(UpdatePatientCommand c) {
        var p = repo.findById(c.id()).orElseThrow(() -> new IllegalArgumentException("Patient not found: "+c.id()));
        if (c.firstName()!=null) p.setFirstName(c.firstName());
        if (c.lastName()!=null)  p.setLastName(c.lastName());
        if (c.email()!=null)     p.setEmail(c.email());
        if (c.age()!=null)       p.setAge(c.age());
        if (c.sex()!=null)       p.setSex(c.sex());
        if (c.bloodType()!=null) p.setBloodType(c.bloodType());
        if (c.patientId()!=null) p.setPatientId(c.patientId());
        if (c.healthInsurance()!=null) p.setHealthInsurance(c.healthInsurance());
        if (c.preferredHospital()!=null) p.setPreferredHospital(c.preferredHospital());
        if (c.status()!=null)     p.setStatus(c.status());
        if (c.registerDate()!=null) p.setRegisterDate(c.registerDate());
        if (c.assignedDoctorId()!=null) {
            if (!doctorRepo.existsById(c.assignedDoctorId()))
                throw new IllegalArgumentException("Assigned doctor not found: " + c.assignedDoctorId());
            p.setAssignedDoctorId(c.assignedDoctorId());
        }
        return repo.save(p);
    }

    @Override public void delete(DeletePatientCommand c) {
        if (!repo.existsById(c.id())) throw new IllegalArgumentException("Patient not found: "+c.id());
        repo.deleteById(c.id());
    }

    @Override public Patient assignDoctor(AssignDoctorToPatientCommand c) {
        var p = repo.findById(c.patientId()).orElseThrow(() -> new IllegalArgumentException("Patient not found: "+c.patientId()));
        if (!doctorRepo.existsById(c.doctorId()))
            throw new IllegalArgumentException("Doctor not found: " + c.doctorId());
        p.setAssignedDoctorId(c.doctorId());
        return repo.save(p);
    }
}
