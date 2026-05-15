package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;

import java.time.LocalDate;

public record PatientResource(Long id, String firstName, String lastName, String email,
                              Integer age, String sex, String bloodType, String patientId,
                              String healthInsurance, String preferredHospital, String status,
                              Long assignedDoctorId, LocalDate registerDate) {
    public static PatientResource from(Patient p){ return new PatientResource(
            p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getAge(), p.getSex(),
            p.getBloodType(), p.getPatientId(), p.getHealthInsurance(), p.getPreferredHospital(),
            p.getStatus(), p.getAssignedDoctorId(), p.getRegisterDate());}
}