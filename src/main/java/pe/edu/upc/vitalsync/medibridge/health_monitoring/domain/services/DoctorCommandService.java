package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.services;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.CreateDoctorCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.DeleteDoctorCommand;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.commands.UpdateDoctorCommand;

public interface DoctorCommandService {
    Doctor create(CreateDoctorCommand c);
    Doctor update(UpdateDoctorCommand c);
    void delete(DeleteDoctorCommand c);
}