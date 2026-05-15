package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.AssignDoctorToPatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreatePatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeletePatientCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdatePatientCommand;

public interface PatientCommandService {
    Patient create(CreatePatientCommand c);
    Patient update(UpdatePatientCommand c);
    void delete(DeletePatientCommand c);
    Patient assignDoctor(AssignDoctorToPatientCommand c);
}