package pe.edu.upc.vitalsync.medibridge.messaging.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infrastructure.persistence.jpa.repositories.DoctorRepository;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.infrastructure.persistence.jpa.repositories.PatientRepository;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.*;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.ConversationCommandService;
import pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories.ConversationRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class ConversationCommandServiceImpl implements ConversationCommandService {

    private final ConversationRepository repo;

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public Conversation create(CreateConversationCommand c) {
        var doctor = doctorRepository.findById(c.doctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + c.doctorId()));
        var patient = patientRepository.findById(c.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + c.patientId()));

        var doctorName  = doctor.getFirstName()  + " " + doctor.getLastName();
        var patientName = patient.getFirstName() + " " + patient.getLastName();

        var conv = new Conversation(c.doctorId(), c.patientId(), doctorName, patientName);
        return repo.save(conv);
    }

    @Override
    public Conversation update(UpdateConversationCommand c) {
        var conv = repo.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found: " + c.id()));

        // Si reasignas el doctor o patient, también refrescamos el nombre
        if (c.doctorId() != null && !c.doctorId().equals(conv.getDoctorId())) {
            var doctor = doctorRepository.findById(c.doctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + c.doctorId()));
            conv.setDoctorId(c.doctorId());
            conv.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
        }

        if (c.patientId() != null && !c.patientId().equals(conv.getPatientId())) {
            var patient = patientRepository.findById(c.patientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + c.patientId()));
            conv.setPatientId(c.patientId());
            conv.setPatientName(patient.getFirstName() + " " + patient.getLastName());
        }

        return repo.save(conv);
    }

    @Override
    public void delete(DeleteConversationCommand c) {
        if (!repo.existsById(c.id())) throw new IllegalArgumentException("Conversation not found: " + c.id());
        repo.deleteById(c.id());
    }
}
