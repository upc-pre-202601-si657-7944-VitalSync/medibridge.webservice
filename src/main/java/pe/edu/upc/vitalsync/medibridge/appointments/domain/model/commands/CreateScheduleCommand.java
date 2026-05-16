package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands;

import java.time.LocalTime;

public record CreateScheduleCommand(
        Long doctorId,
        String dayOfWeek,       // "MONDAY"..."SUNDAY"
        LocalTime startTime,
        LocalTime endTime,
        Boolean isAvailable
) {}
