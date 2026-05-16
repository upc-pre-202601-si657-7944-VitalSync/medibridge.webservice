package pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.transform;

import pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates.Schedule;
import pe.edu.upc.vitalsync.medibridge.appointments.interfaces.rest.resources.ScheduleResource;

import java.util.List;

public class ScheduleResourceAssembler {
    public static ScheduleResource toResource(Schedule s) {
        return ScheduleResource.fromEntity(s);
    }
    public static List<ScheduleResource> toResourceList(List<Schedule> list) {
        return list.stream().map(ScheduleResource::fromEntity).toList();
    }
}
