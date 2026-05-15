package pe.edu.upc.vitalsync.medibridge.messaging.application.internal.commandservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.*;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.services.MessageCommandService;
import pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories.MessageRepository;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories.ConversationRepository;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepo;
    private final ConversationRepository conversationRepo;

    @Override
    public Message create(CreateMessageCommand c) {
        // 1) Guardar mensaje
        var message = new Message(
                c.conversationId(),
                c.senderId(), c.senderName(),
                c.receiverId(), c.receiverName(),
                c.subject(), c.content()
        );
        var saved = messageRepo.save(message);

        // 2) Actualizar la conversación asociada
        var maybeConv = conversationRepo.findById(c.conversationId());
        if (maybeConv.isEmpty()) {
            throw new IllegalArgumentException("Conversation not found: " + c.conversationId());
        }
        var conversation = maybeConv.get();

        // lastMessagePreview se toma SOLO del content
        String preview = c.content() != null && !c.content().isBlank()
                ? (c.content().length() > 120 ? c.content().substring(0, 120) : c.content())
                : "";

        boolean fromDoctor = c.senderId().equals(conversation.getDoctorId());
        conversation.setLastMessagePreview(preview);
        conversation.setLastMessageAt(Date.from(saved.getSentAt()));

        if (fromDoctor) {
            // Incrementar no leídos del paciente
            Integer ufp = conversation.getUnreadForPatient() == null ? 0 : conversation.getUnreadForPatient();
            conversation.setUnreadForPatient(ufp + 1);
        } else {
            // Incrementar no leídos del doctor
            Integer ufd = conversation.getUnreadForDoctor() == null ? 0 : conversation.getUnreadForDoctor();
            conversation.setUnreadForDoctor(ufd + 1);
        }

        conversationRepo.save(conversation);
        return saved;
    }

    @Override
    public Message update(UpdateMessageCommand c) {
        var message = messageRepo.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + c.id()));
        if (c.subject() != null) message.setSubject(c.subject());
        if (c.content() != null) message.setContent(c.content());
        return messageRepo.save(message);
    }

    @Override
    public void delete(DeleteMessageCommand c) {
        var msg = messageRepo.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + c.id()));

        Long conversationId = msg.getConversationId();
        messageRepo.deleteById(c.id());

        // Recalcular el estado de la conversación con el último mensaje restante
        var maybeConv = conversationRepo.findById(conversationId);
        if (maybeConv.isEmpty()) {
            throw new IllegalArgumentException("Conversation not found: " + conversationId);
        }
        var conv = maybeConv.get();

        var latest = messageRepo.findTopByConversationIdOrderBySentAtDesc(conversationId);
        if (latest != null) {
            // preview del último mensaje = content
            String contentPreview = latest.getContent() != null
                    ? (latest.getContent().length() > 120 ? latest.getContent().substring(0, 120) : latest.getContent())
                    : "";
            conv.setLastMessagePreview(contentPreview);
            conv.setLastMessageAt(Date.from(latest.getSentAt()));
        } else {
            conv.setLastMessagePreview(null);
            conv.setLastMessageAt(null);
            conv.setUnreadForDoctor(0);
            conv.setUnreadForPatient(0);
        }
        conversationRepo.save(conv);
    }

    @Override
    public Message markAsRead(MarkMessageAsReadCommand c) {
        var message = messageRepo.findById(c.id())
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + c.id()));

        if (!message.isRead()) {
            message.setRead(true);
            message = messageRepo.save(message);

            var maybeConv = conversationRepo.findById(message.getConversationId());
            if (maybeConv.isEmpty()) {
                throw new IllegalArgumentException("Conversation not found: " + message.getConversationId());
            }
            var conversation = maybeConv.get();

            // Ajustar contadores de no leídos
            if (message.getReceiverId().equals(conversation.getDoctorId())) {
                int current = conversation.getUnreadForDoctor() == null ? 0 : conversation.getUnreadForDoctor();
                conversation.setUnreadForDoctor(Math.max(0, current - 1));
            } else if (message.getReceiverId().equals(conversation.getPatientId())) {
                int current = conversation.getUnreadForPatient() == null ? 0 : conversation.getUnreadForPatient();
                conversation.setUnreadForPatient(Math.max(0, current - 1));
            }
            conversationRepo.save(conversation);
        }
        return message;
    }
}
