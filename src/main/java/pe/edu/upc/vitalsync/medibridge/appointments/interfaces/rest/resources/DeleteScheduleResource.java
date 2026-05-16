package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteScheduleCommand;

public record DeleteScheduleResource(Long id) {
    public DeleteScheduleCommand toCommand() { return new DeleteScheduleCommand(id); }
}
