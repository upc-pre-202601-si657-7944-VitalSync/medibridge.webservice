package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;


@Entity
@Table(name = "doctors",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_doctor_email", columnNames = "email"),
                @UniqueConstraint(name="uk_doctor_cmp", columnNames = "cmp_code")
        })
@Getter @Setter @NoArgsConstructor
public class Doctor extends AuditableAbstractAggregateRoot<Doctor> {

    @Column(name="first_name", nullable=false) @NotBlank private String firstName;
    @Column(name="last_name", nullable=false)  @NotBlank private String lastName;

    @Column(nullable=false) @Email private String email;

    @Column(nullable=false, name="specialization") @NotBlank private String specialization;
    @Column(nullable=false, name="cmp_code")       @NotBlank private String cmpCode;

    @Column(nullable=false, name="study_centre")   @NotBlank private String studyCentre;
    @Column(nullable=false, name="phone_number")   @NotBlank private String phoneNumber;

    public Doctor(String firstName, String lastName, String email, String specialization,
                  String cmpCode, String studyCentre, String phoneNumber) {
        this.firstName = firstName; this.lastName = lastName; this.email = email;
        this.specialization = specialization; this.cmpCode = cmpCode;
        this.studyCentre = studyCentre; this.phoneNumber = phoneNumber;
    }
}
