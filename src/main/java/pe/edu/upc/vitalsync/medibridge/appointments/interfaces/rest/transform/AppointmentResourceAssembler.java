package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.transform;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Appointment;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources.AppointmentResource;

import java.util.List;

public class AppointmentResourceAssembler {

    public static AppointmentResource toResource(Appointment entity) {
        return AppointmentResource.fromEntity(entity);
    }

    public static List<AppointmentResource> toResourceList(List<Appointment> entities) {
        return entities.stream()
                .map(AppointmentResource::fromEntity)
                .toList();
    }
}
