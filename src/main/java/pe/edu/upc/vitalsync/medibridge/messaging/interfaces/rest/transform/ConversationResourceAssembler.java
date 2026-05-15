package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.transform;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources.ConversationResource;

import java.util.List;

public class ConversationResourceAssembler {
    public static ConversationResource toResource(Conversation c) { return ConversationResource.from(c); }
    public static List<ConversationResource> toResourceList(List<Conversation> list) {
        return list.stream().map(ConversationResource::from).toList();
    }
}
