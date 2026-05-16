package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AppointmentTest {

    @Test
    @DisplayName("Appointment constructor should set all fields (AAA)")
    void constructor_ShouldSetAllFields() {
        // Arrange
        Long patientId = 1L;
        String patientName = "Luis Perez";
        Long doctorId = 2L;
        String doctorName = "Dra. Ana Ruiz";
        LocalDate appointmentDate = LocalDate.of(2024, 6, 1);
        LocalTime appointmentTime = LocalTime.of(9, 30);
        String reason = "Checkup";
        String status = "Scheduled";
        String notes = "Bring reports";

        // Act
        Appointment appointment = new Appointment(
                patientId,
                patientName,
                doctorId,
                doctorName,
                appointmentDate,
                appointmentTime,
                reason,
                status,
                notes,
                "ignored"
        );

        // Assert
        assertEquals(patientId, appointment.getPatientId());
        assertEquals(patientName, appointment.getPatientName());
        assertEquals(doctorId, appointment.getDoctorId());
        assertEquals(doctorName, appointment.getDoctorName());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(appointmentTime, appointment.getAppointmentTime());
        assertEquals(reason, appointment.getReason());
        assertEquals(status, appointment.getStatus());
        assertEquals(notes, appointment.getNotes());
    }

    @Test
    @DisplayName("updateAppointment should update reason, status, notes (AAA)")
    void updateAppointment_ShouldUpdateFields() {
        // Arrange
        Appointment appointment = new Appointment(
                1L,
                "Luis Perez",
                2L,
                "Dra. Ana Ruiz",
                LocalDate.of(2024, 6, 1),
                LocalTime.of(9, 30),
                "Checkup",
                "Scheduled",
                "Bring reports",
                "ignored"
        );

        String newReason = "Follow-up";
        String newStatus = "Completed";
        String newNotes = "All good";

        // Act
        appointment.updateAppointment(newReason, newStatus, newNotes);

        // Assert
        assertEquals(newReason, appointment.getReason());
        assertEquals(newStatus, appointment.getStatus());
        assertEquals(newNotes, appointment.getNotes());
    }
}
