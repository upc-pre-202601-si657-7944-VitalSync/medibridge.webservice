package pe.edu.upc.vitalsync.medibridge.appointments.domain.services;


import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.CreateAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.DeleteAppointmentCommand;
import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Appointment create(CreateAppointmentCommand command);
    Appointment update(UpdateAppointmentCommand command);
    void delete(DeleteAppointmentCommand command);
}
