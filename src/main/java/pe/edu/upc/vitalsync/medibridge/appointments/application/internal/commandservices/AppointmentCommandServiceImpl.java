package pe.edu.upc.vitalsync.medibridge.appointments.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.UpdateAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentCommandServiceImpl implements pe.edu.upc.vitalsync.medibridge..appointments.domain.services.AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment create(CreateAppointmentCommand c) {
        var entity = new Appointment(
                c.patientId(), c.patientName(),
                c.doctorId(), c.doctorName(),
                c.appointmentDate(), c.appointmentTime(),
                c.reason(), c.status(), c.notes(), null
        );
        return appointmentRepository.save(entity);
    }

    @Override
    public Appointment update(UpdateAppointmentCommand c) {
        var entity = appointmentRepository.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found: " + c.id()));

        // Reglas simples de actualización (ajusta a tu método de dominio si lo tienes)
        entity.setPatientId(c.patientId());
        entity.setPatientName(c.patientName());
        entity.setDoctorId(c.doctorId());
        entity.setDoctorName(c.doctorName());
        entity.setAppointmentDate(c.appointmentDate());
        entity.setAppointmentTime(c.appointmentTime());
        entity.setReason(c.reason());
        entity.setStatus(c.status());
        entity.setNotes(c.notes());

        return appointmentRepository.save(entity);
    }

    @Override
    public void delete(DeleteAppointmentCommand c) {
        if (!appointmentRepository.existsById(c.id()))
            throw new IllegalArgumentException("Appointment not found: " + c.id());
        appointmentRepository.deleteById(c.id());
    }
}
