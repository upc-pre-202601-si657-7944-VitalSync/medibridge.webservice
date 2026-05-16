package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteAppointmentCommand;

public record DeleteAppointmentResource(Long id) {
    public DeleteAppointmentCommand toCommand() {
        return new DeleteAppointmentCommand(id);
    }
}