package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.CreateMessageCommand;

public record CreateMessageResource(
        @NotNull Long conversationId,
        @NotNull Long senderId,
        @NotBlank String senderName,
        @NotNull Long receiverId,
        @NotBlank String receiverName,
        String subject,
        @NotBlank String content
) {
    public CreateMessageCommand toCommand() {
        return new CreateMessageCommand(conversationId, senderId, senderName,
                receiverId, receiverName, subject, content);
    }
}
