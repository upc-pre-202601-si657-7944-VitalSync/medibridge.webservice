package pe.edu.upc.vitalsync.medibridge.appointments.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Schedule;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.CreateScheduleCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteScheduleCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.UpdateScheduleCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.infrastructure.persistence.jpa.repositories.ScheduleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleCommandServiceImpl implements pe.edu.upc.vitalsync.medibridge.appointments.domain.services.ScheduleCommandService {

    private final ScheduleRepository repository;

    @Override
    public Schedule create(CreateScheduleCommand c) {
        validateTimes(c.startTime(), c.endTime());
        var s = new Schedule(c.doctorId(), c.dayOfWeek(), c.startTime(), c.endTime(), c.isAvailable());
        return repository.save(s);
    }

    @Override
    public Schedule update(UpdateScheduleCommand c) {
        var s = repository.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found: " + c.id()));
        if (c.startTime() != null && c.endTime() != null) validateTimes(c.startTime(), c.endTime());

        if (c.doctorId() != null) s.setDoctorId(c.doctorId());
        if (c.dayOfWeek() != null) s.setDayOfWeek(c.dayOfWeek());
        if (c.startTime() != null) s.setStartTime(c.startTime());
        if (c.endTime() != null) s.setEndTime(c.endTime());
        if (c.isAvailable() != null) s.setIsAvailable(c.isAvailable());

        return repository.save(s);
    }

    @Override
    public void delete(DeleteScheduleCommand c) {
        if (!repository.existsById(c.id()))
            throw new IllegalArgumentException("Schedule not found: " + c.id());
        repository.deleteById(c.id());
    }

    private void validateTimes(java.time.LocalTime start, java.time.LocalTime end) {
        if (!start.isBefore(end)) throw new IllegalArgumentException("startTime must be before endTime");
    }
}
