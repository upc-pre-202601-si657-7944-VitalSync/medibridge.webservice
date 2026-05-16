package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.DeleteConversationCommand;

public record DeleteConversationResource(Long id) {
    public DeleteConversationCommand toCommand(){ return new DeleteConversationCommand(id); }
}
