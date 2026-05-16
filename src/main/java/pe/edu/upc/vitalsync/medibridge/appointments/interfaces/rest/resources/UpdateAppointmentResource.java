package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.UpdateAppointmentCommand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public record UpdateAppointmentResource(
        // Todos opcionales excepto el id que va en la ruta
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        String appointmentDate, // ISO yyyy-MM-dd
        String appointmentTime, // ISO HH:mm[:ss]
        String reason,
        String status,
        String notes
) {
    public UpdateAppointmentCommand toCommand(@NotNull Long id,
                                              // valores actuales para merge (puedes pasarlos desde el service)
                                              Long currentPatientId,
                                              String currentPatientName,
                                              Long currentDoctorId,
                                              String currentDoctorName,
                                              LocalDate currentDate,
                                              LocalTime currentTime,
                                              String currentReason,
                                              String currentStatus,
                                              String currentNotes) {
        return new UpdateAppointmentCommand(
                id,
                Optional.ofNullable(patientId).orElse(currentPatientId),
                Optional.ofNullable(patientName).orElse(currentPatientName),
                Optional.ofNullable(doctorId).orElse(currentDoctorId),
                Optional.ofNullable(doctorName).orElse(currentDoctorName),
                Optional.ofNullable(appointmentDate).map(LocalDate::parse).orElse(currentDate),
                Optional.ofNullable(appointmentTime).map(LocalTime::parse).orElse(currentTime),
                Optional.ofNullable(reason).orElse(currentReason),
                Optional.ofNullable(status).orElse(currentStatus),
                Optional.ofNullable(notes).orElse(currentNotes)
        );
    }
}
