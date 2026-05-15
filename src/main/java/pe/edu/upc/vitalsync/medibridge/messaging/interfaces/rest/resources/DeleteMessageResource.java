package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.DeleteMessageCommand;

public record DeleteMessageResource(Long id) {
    public DeleteMessageCommand toCommand() {
        return new DeleteMessageCommand(id);
    }
}
