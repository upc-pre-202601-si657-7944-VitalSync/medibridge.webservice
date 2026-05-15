package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands;

import java.time.LocalDate;

public record UpdatePatientCommand(Long id, String firstName, String lastName, String email,
                                   Integer age, String sex, String bloodType,
                                   String patientId, String healthInsurance, String preferredHospital,
                                   String status, Long assignedDoctorId, LocalDate registerDate) {}
