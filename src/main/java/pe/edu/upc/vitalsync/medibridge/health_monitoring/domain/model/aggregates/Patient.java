package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;


import java.time.LocalDate;

@Entity
@Table(name="patients",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_patient_email", columnNames = "email"),
                @UniqueConstraint(name="uk_patient_pid", columnNames = "patient_id")
        })
@Getter @Setter @NoArgsConstructor
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    @Column(name="first_name", nullable=false) @NotBlank private String firstName;
    @Column(name="last_name", nullable=false)  @NotBlank private String lastName;

    @Column(nullable=false) @Email private String email;

    @Column(nullable=false)              @NotNull private Integer age;
    @Column(nullable=false, name="sex")  @NotBlank private String sex;         // "Male", "Female", etc.
    @Column(nullable=false, name="blood_type") @NotBlank private String bloodType; // "A+", "O+", etc.

    @Column(nullable=false, name="patient_id") @NotBlank private String patientId;
    @Column(nullable=false, name="health_insurance") @NotBlank private String healthInsurance;
    @Column(nullable=false, name="preferred_hospital") @NotBlank private String preferredHospital;

    @Column(nullable=false, name="status") @NotBlank private String status; // "Healthy", "Under Observation", etc.

    @Column(name="assigned_doctor_id") private Long assignedDoctorId; // Nullable

    @Column(nullable=false, name="register_date") @NotNull private LocalDate registerDate;

    public Patient(String firstName, String lastName, String email, Integer age, String sex, String bloodType,
                   String patientId, String healthInsurance, String preferredHospital, String status,
                   Long assignedDoctorId, LocalDate registerDate) {
        this.firstName = firstName; this.lastName = lastName; this.email = email;
        this.age = age; this.sex = sex; this.bloodType = bloodType;
        this.patientId = patientId; this.healthInsurance = healthInsurance; this.preferredHospital = preferredHospital;
        this.status = status; this.assignedDoctorId = assignedDoctorId; this.registerDate = registerDate;
    }
}
