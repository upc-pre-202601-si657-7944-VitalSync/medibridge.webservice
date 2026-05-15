package pe.edu.upc.vitalsync.medibridge.messaging.domain.services;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.*;

public interface ConversationCommandService {
    Conversation create(CreateConversationCommand c);
    Conversation update(UpdateConversationCommand c);
    void delete(DeleteConversationCommand c);
}
