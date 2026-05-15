package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands;

public record UpdateMessageCommand(
        Long id,
        String subject,
        String content
) {}
