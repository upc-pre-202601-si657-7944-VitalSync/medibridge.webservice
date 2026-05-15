package pe.edu.upc.vitalsync.medibridge.messaging.domain.services;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;
import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands.*;

public interface MessageCommandService {
    Message create(CreateMessageCommand command);
    Message update(UpdateMessageCommand command);
    void delete(DeleteMessageCommand command);
    Message markAsRead(MarkMessageAsReadCommand command);
}
