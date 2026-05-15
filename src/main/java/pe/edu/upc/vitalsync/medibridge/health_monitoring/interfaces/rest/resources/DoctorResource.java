package pe.edu.upc.vitalsync.medibridge.health_monitoring.interfaces.rest.resources;


import pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates.Doctor;

public record DoctorResource(Long id, String firstName, String lastName, String email,
                             String specialization, String cmpCode, String studyCentre, String phoneNumber) {
    public static DoctorResource from(Doctor d){ return new DoctorResource(d.getId(), d.getFirstName(), d.getLastName(),
            d.getEmail(), d.getSpecialization(), d.getCmpCode(), d.getStudyCentre(), d.getPhoneNumber());}
}