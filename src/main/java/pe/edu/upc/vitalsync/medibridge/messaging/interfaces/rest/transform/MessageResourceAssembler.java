package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.transform;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Message;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources.MessageResource;

import java.util.List;

public class MessageResourceAssembler {
    public static MessageResource toResource(Message m) { return MessageResource.from(m); }
    public static List<MessageResource> toResourceList(List<Message> list) {
        return list.stream().map(MessageResource::from).toList();
    }
}
