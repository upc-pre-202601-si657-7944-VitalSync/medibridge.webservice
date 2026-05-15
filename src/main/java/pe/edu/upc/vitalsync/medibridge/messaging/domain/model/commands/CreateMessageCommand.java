package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands;

public record CreateMessageCommand(
        Long conversationId,
        Long senderId,
        String senderName,
        Long receiverId,
        String receiverName,
        String subject,
        String content
) {}
