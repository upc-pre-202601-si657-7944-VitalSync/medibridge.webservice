package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands;

public record CreateDoctorCommand(String firstName, String lastName, String email,
                                  String specialization, String cmpCode,
                                  String studyCentre, String phoneNumber) {}