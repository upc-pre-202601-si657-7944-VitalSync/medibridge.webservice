package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.CreateAppointmentCommand;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentResource(
        @NotNull Long patientId,
        String patientName,
        @NotNull Long doctorId,
        String doctorName,
        @NotBlank String appointmentDate,  // "yyyy-MM-dd"
        @NotBlank String appointmentTime,  // "HH:mm[:ss]"
        @NotBlank String reason,
        @NotBlank String status,
        String notes
) {
    public CreateAppointmentCommand toCommand() {
        return new CreateAppointmentCommand(
                patientId,
                patientName,
                doctorId,
                doctorName,
                LocalDate.parse(appointmentDate),
                LocalTime.parse(appointmentTime),
                reason,
                status,
                notes
        );
    }
}