package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.UpdateScheduleCommand;

import java.time.LocalTime;

public record UpdateScheduleResource(
        Long doctorId,
        String dayOfWeek,
        String startTime,
        String endTime,
        Boolean isAvailable
) {
    public UpdateScheduleCommand toCommand(Long id,
                                           Long currentDoctorId,
                                           String currentDayOfWeek,
                                           LocalTime currentStart,
                                           LocalTime currentEnd,
                                           Boolean currentIsAvailable) {
        return new UpdateScheduleCommand(
                id,
                doctorId != null ? doctorId : currentDoctorId,
                dayOfWeek != null ? dayOfWeek : currentDayOfWeek,
                startTime != null ? LocalTime.parse(startTime) : currentStart,
                endTime != null ? LocalTime.parse(endTime) : currentEnd,
                isAvailable != null ? isAvailable : currentIsAvailable
        );
    }
}
