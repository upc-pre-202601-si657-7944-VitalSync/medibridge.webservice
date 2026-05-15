package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;

import java.util.List;

public record AssignDoctorsToPatientsResource(List<Item> assignments) {
    public record Item(Long patientId, Long doctorId) {}
}