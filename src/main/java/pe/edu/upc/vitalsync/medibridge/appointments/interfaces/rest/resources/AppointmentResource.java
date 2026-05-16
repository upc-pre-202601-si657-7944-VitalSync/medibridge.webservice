package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResource(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        String reason,
        String status,
        String notes
) {
    public static AppointmentResource fromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getPatientName(),
                entity.getDoctorId(),
                entity.getDoctorName(),
                entity.getAppointmentDate(),
                entity.getAppointmentTime(),
                entity.getReason(),
                entity.getStatus(),
                entity.getNotes()
        );
    }
}
