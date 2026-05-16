package pe.edu.upc.vitalsync.medibridge.appointments.application.internal.queryservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Schedule;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.services.ScheduleQueryService;
import pe.edu.upc.vitalsync.medibridge.appointments.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository repository;

    @Override
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Schedule> getById(Long id) {
        return repository.findById(id);
    }

}
