package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdateDoctorCommand;

public record UpdateDoctorResource(String firstName, String lastName, String email,
                                   String specialization, String cmpCode, String studyCentre, String phoneNumber) {
    public UpdateDoctorCommand toCommand(Long id){
        return new UpdateDoctorCommand(id, firstName, lastName, email, specialization, cmpCode, studyCentre, phoneNumber);
    }
}