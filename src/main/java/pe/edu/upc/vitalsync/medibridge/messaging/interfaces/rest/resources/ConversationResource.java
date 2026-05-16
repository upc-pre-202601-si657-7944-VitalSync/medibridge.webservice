package pe.edu.upc.vitalsync.medibridge.messaging.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates.Conversation;
import java.util.Date;

public record ConversationResource(
        Long id,
        Long doctorId,
        Long patientId,
        String doctorName,
        String patientName,
        String lastMessagePreview,
        Date lastMessageAt,
        Integer unreadForDoctor,
        Integer unreadForPatient,
        Date createdAt,
        Date updatedAt
) {
    public static ConversationResource from(Conversation c) {
        return new ConversationResource(
                c.getId(),
                c.getDoctorId(),
                c.getPatientId(),
                c.getDoctorName(),
                c.getPatientName(),
                c.getLastMessagePreview(),
                c.getLastMessageAt(),
                c.getUnreadForDoctor(),
                c.getUnreadForPatient(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }
}
