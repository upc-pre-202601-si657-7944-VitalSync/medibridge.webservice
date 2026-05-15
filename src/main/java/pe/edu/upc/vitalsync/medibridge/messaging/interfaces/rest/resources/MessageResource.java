package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;
import java.time.Instant;

public record MessageResource(
        Long id,
        Long conversationId,
        Long senderId,
        String senderName,
        Long receiverId,
        String receiverName,
        String subject,
        String content,
        Instant sentAt,
        boolean isRead
) {
    public static MessageResource from(Message m) {
        return new MessageResource(
                m.getId(), m.getConversationId(),
                m.getSenderId(), m.getSenderName(),
                m.getReceiverId(), m.getReceiverName(),
                m.getSubject(), m.getContent(),
                m.getSentAt(), m.isRead()
        );
    }
}
