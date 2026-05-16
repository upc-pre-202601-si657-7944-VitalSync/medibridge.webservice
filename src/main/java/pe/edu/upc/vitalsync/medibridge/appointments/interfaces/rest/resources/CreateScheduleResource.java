package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.CreateScheduleCommand;

import java.time.LocalTime;

public record CreateScheduleResource(
        @NotNull Long doctorId,
        @NotNull @Pattern(regexp = "MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY")
        String dayOfWeek,
        @NotNull String startTime,     // "HH:mm[:ss]"
        @NotNull String endTime,       // "HH:mm[:ss]"
        @NotNull Boolean isAvailable
) {
    public CreateScheduleCommand toCommand() {
        return new CreateScheduleCommand(
                doctorId, dayOfWeek,
                LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                isAvailable
        );
    }
}
