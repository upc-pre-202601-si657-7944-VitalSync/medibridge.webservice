package pe.edu.upc.vitalsync.medibridge.messaging.domain.services;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;

import java.util.List;
import java.util.Optional;

public interface ConversationQueryService {
    List<Conversation> getAll();
    Optional<Conversation> getById(Long id);
}
