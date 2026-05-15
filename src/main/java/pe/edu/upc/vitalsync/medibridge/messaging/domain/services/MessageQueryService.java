package pe.edu.upc.vitalsync.medibridge.messaging.domain.services;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<Message> getAll();
    Optional<Message> getById(Long id);
    List<Message> getByConversation(Long conversationId);
}
