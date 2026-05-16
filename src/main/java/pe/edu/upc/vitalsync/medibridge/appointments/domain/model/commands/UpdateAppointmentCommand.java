package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateAppointmentCommand(
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
) {}