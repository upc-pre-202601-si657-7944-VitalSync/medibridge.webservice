package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;

import java.time.Instant;

@Entity
@Table(name = "messages",
        indexes = {
                @Index(name="idx_conversation", columnList="conversation_id")
        })
@Getter
@Setter
@NoArgsConstructor
public class Message extends AuditableAbstractAggregateRoot<Message> {

    @NotNull
    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @NotNull
    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @NotNull
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "sent_at", nullable = false)
    private Instant sentAt = Instant.now();

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    public Message(Long conversationId, Long senderId, String senderName,
                   Long receiverId, String receiverName,
                   String subject, String content) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.subject = subject;
        this.content = content;
        this.sentAt = Instant.now();
        this.isRead = false;
    }
}
