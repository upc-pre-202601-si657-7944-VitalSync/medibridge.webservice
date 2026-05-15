package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreateDoctorCommand;

public record CreateDoctorResource(
        @NotBlank String firstName, @NotBlank String lastName,
        @Email @NotBlank String email, @NotBlank String specialization,
        @NotBlank String cmpCode, @NotBlank String studyCentre, @NotBlank String phoneNumber
){
    public CreateDoctorCommand toCommand(){ return new CreateDoctorCommand(firstName, lastName, email, specialization, cmpCode, studyCentre, phoneNumber); }
}