package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.vitalsync.medibridge.shared.domain.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
public class Schedule extends AuditableAbstractAggregateRoot<Schedule> {


    // ---------- Doctor Reference ----------
    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    // ---------- Day & Time ----------
    @NotBlank
    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek; // Ejemplo: "Monday", "Tuesday", etc.

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    // ---------- Availability ----------
    @NotNull
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    // ---------- Constructor ----------
    public Schedule(Long doctorId,
                    String dayOfWeek,
                    LocalTime startTime,
                    LocalTime endTime,
                    Boolean isAvailable) {
        this.doctorId = doctorId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    // ---------- Domain Logic ----------
    public void updateAvailability(Boolean availability) {
        this.isAvailable = availability;
    }

    public void updateTimeRange(LocalTime newStart, LocalTime newEnd) {
        this.startTime = newStart;
        this.endTime = newEnd;
    }
}
