package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.MarkMessageAsReadCommand;

public record MarkAsReadResource(Long id) {
    public MarkMessageAsReadCommand toCommand() {
        return new MarkMessageAsReadCommand(id);
    }
}
