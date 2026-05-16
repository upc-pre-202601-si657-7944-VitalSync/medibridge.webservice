package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteScheduleCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.services.ScheduleCommandService;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.services.ScheduleQueryService;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources.*;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.transform.ScheduleResourceAssembler;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class SchedulesController {

    private final ScheduleCommandService commandService;
    private final ScheduleQueryService queryService;

    // loadSchedules (GET all) + filtros opcionales
    @GetMapping
    public ResponseEntity<?> getAll(
      //      @RequestParam(required = false) Long doctorId,
      //      @RequestParam(required = false) String dayOfWeek
    ) {
        return ResponseEntity.ok(ScheduleResourceAssembler
                .toResourceList(queryService.getAll()));
    }

    // getScheduleById
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResource> getById(@PathVariable Long id) {
        return queryService.getById(id)
                .map(ScheduleResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // addSchedule
    @PostMapping
    public ResponseEntity<ScheduleResource> create(@Valid @RequestBody CreateScheduleResource body) {
        var created = commandService.create(body.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ScheduleResourceAssembler.toResource(created));
    }

    // updateSchedule
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResource> update(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateScheduleResource body) {
        var current = queryService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found: " + id));
        var updated = commandService.update(body.toCommand(
                id,
                current.getDoctorId(),
                current.getDayOfWeek(),
                current.getStartTime(),
                current.getEndTime(),
                current.getIsAvailable()
        ));
        return ResponseEntity.ok(ScheduleResourceAssembler.toResource(updated));
    }

    // deleteScheduleById
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(new DeleteScheduleCommand(id));
        return ResponseEntity.noContent().build();
    }
}
