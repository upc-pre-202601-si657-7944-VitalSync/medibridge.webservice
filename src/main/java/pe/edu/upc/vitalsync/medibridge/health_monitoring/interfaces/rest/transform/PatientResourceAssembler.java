package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.transform;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Patient;
import pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources.PatientResource;

import java.util.List;

public class PatientResourceAssembler {
    public static PatientResource toResource(Patient patient) {
        return PatientResource.from(patient);
    }

    public static List<PatientResource> toResourceList(List<Patient> patients) {
        return patients.stream().map(PatientResource::from).toList();
    }
}