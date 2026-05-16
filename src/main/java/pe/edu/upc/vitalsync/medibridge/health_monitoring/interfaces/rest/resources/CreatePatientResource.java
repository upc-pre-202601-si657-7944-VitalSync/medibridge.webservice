package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreatePatientCommand;

import java.time.LocalDate;

public record CreatePatientResource(
        @NotBlank String firstName, @NotBlank String lastName,
        @Email @NotBlank String email, @NotNull Integer age,
        @NotBlank String sex, @NotBlank String bloodType,
        @NotBlank String patientId, @NotBlank String healthInsurance,
        @NotBlank String preferredHospital, @NotBlank String status,
        Long assignedDoctorId, @NotBlank String registerDate // "yyyy-MM-dd"
){
    public CreatePatientCommand toCommand(){
        return new CreatePatientCommand(firstName, lastName, email, age, sex, bloodType,
                patientId, healthInsurance, preferredHospital, status, assignedDoctorId, LocalDate.parse(registerDate));
    }
}