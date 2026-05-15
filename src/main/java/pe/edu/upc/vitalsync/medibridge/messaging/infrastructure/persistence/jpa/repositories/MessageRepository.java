package pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationIdOrderBySentAtAsc(Long conversationId);
    Message findTopByConversationIdOrderBySentAtDesc(Long conversationId);

}
