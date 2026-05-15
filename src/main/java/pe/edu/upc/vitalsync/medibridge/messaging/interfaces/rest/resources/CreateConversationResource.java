package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.CreateConversationCommand;

public record CreateConversationResource(
        @NotNull Long doctorId,
        @NotNull Long patientId
){
    public CreateConversationCommand toCommand() {
        return new CreateConversationCommand(doctorId, patientId);
    }
}
