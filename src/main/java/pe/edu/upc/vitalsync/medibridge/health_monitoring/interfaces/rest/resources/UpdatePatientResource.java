package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdatePatientCommand;

import java.time.LocalDate;

public record UpdatePatientResource(
        String firstName, String lastName, String email, Integer age,
        String sex, String bloodType, String patientId, String healthInsurance,
        String preferredHospital, String status, Long assignedDoctorId, String registerDate // ISO
){
    public UpdatePatientCommand toCommand(Long id){
        return new UpdatePatientCommand(id, firstName, lastName, email, age, sex, bloodType,
                patientId, healthInsurance, preferredHospital, status, assignedDoctorId,
                registerDate!=null? LocalDate.parse(registerDate): null);
    }
}