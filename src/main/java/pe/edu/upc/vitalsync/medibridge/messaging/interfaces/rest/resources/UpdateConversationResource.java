package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.UpdateConversationCommand;

public record UpdateConversationResource(
        Long doctorId,
        Long patientId
){
    public UpdateConversationCommand toCommand(Long id) {
        return new UpdateConversationCommand(id, doctorId, patientId);
    }
}
