package pe.edu.upc.vitalsync.medibridge.messaging.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {}
