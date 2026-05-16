package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MessageTest {

    @Test
    @DisplayName("Message constructor should set all fields and defaults (AAA)")
    void constructor_ShouldSetAllFieldsAndDefaults() {
        // Arrange
        Long conversationId = 101L;
        Long senderId = 11L;
        String senderName = "Dr. Ana";
        Long receiverId = 22L;
        String receiverName = "Luis";
        String subject = "Consulta";
        String content = "Tu cita es manana";

        // Act
        Message message = new Message(
                conversationId,
                senderId,
                senderName,
                receiverId,
                receiverName,
                subject,
                content
        );

        // Assert
        assertEquals(conversationId, message.getConversationId());
        assertEquals(senderId, message.getSenderId());
        assertEquals(senderName, message.getSenderName());
        assertEquals(receiverId, message.getReceiverId());
        assertEquals(receiverName, message.getReceiverName());
        assertEquals(subject, message.getSubject());
        assertEquals(content, message.getContent());
        assertNotNull(message.getSentAt());
        assertFalse(message.isRead());
    }
}
