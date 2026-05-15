package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeletePatientCommand;

public record DeletePatientResource(Long id) {

    public DeletePatientCommand toCommand() {
        return new DeletePatientCommand(id);
    }
}