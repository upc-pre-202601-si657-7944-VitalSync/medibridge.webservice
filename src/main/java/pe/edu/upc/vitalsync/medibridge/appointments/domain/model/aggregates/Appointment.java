package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {


    // ---------- Relationships / References ----------
    @NotNull
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "patient_name")
    private String patientName;

    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    // ---------- Schedule ----------
    @NotNull
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @NotNull
    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    // ---------- Details ----------
    @NotBlank
    @Column(name = "reason", nullable = false)
    private String reason;

    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // ---------- Constructor ----------
    public Appointment(Long patientId,
                       String patientName,
                       Long doctorId,
                       String doctorName,
                       LocalDate appointmentDate,
                       LocalTime appointmentTime,
                       String reason,
                       String status,
                       String notes,
                       String createdAt) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
        this.notes = notes;
    }

    // ---------- Update Logic (Domain method) ----------
    public Appointment updateAppointment(String reason, String status, String notes) {
        this.reason = reason;
        this.status = status;
        this.notes = notes;
        return this;
    }
}
