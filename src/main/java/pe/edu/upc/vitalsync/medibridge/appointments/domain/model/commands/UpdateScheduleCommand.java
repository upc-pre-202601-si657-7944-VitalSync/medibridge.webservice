package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands;

import java.time.LocalTime;

public record UpdateScheduleCommand(
        Long id,
        Long doctorId,
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isAvailable
) {}
