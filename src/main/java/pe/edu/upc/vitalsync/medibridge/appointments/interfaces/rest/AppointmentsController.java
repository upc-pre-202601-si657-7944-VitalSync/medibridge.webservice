package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.services.*;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources.*;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.transform.AppointmentResourceAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentsController {

    private final AppointmentCommandService commandService;
    private final AppointmentQueryService queryService;

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<AppointmentResource> create(@Valid @RequestBody CreateAppointmentResource resource) {
        var created = commandService.create(resource.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AppointmentResourceAssembler.toResource(created));
    }

    // ---------- GET ALL ----------
    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAll() {
        var appointments = queryService.getAll();
        return ResponseEntity.ok(AppointmentResourceAssembler.toResourceList(appointments));
    }

    // ---------- GET BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResource> getById(@PathVariable Long id) {
        return queryService.getById(id)
                .map(AppointmentResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- UPDATE ----------
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResource> update(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateAppointmentResource resource) {
        var updated = commandService.update(resource.toCommand(
                id, null, null, null, null, null, null, null, null, null
        ));
        return ResponseEntity.ok(AppointmentResourceAssembler.toResource(updated));
    }

    // ---------- DELETE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(new DeleteAppointmentCommand(id));
        return ResponseEntity.noContent().build();
    }
}
