package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.commands;

public record UpdateConversationCommand(Long id, Long doctorId, Long patientId) {}
