package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeleteDoctorCommand;

public record DeleteDoctorResource(Long id) {

    public DeleteDoctorCommand toCommand() {
        return new DeleteDoctorCommand(id);
    }
}