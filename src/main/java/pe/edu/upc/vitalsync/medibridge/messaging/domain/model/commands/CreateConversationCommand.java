package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands;

public record CreateConversationCommand(Long doctorId, Long patientId) {}
