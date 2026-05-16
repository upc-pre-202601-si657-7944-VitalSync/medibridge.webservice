package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DoctorTest {

    @Test
    @DisplayName("Doctor constructor should set all fields (AAA)")
    void constructor_ShouldSetAllFields() {
        // Arrange
        String firstName = "Ana";
        String lastName = "Ruiz";
        String email = "ana.ruiz@example.com";
        String specialization = "Cardiology";
        String cmpCode = "CMP-12345";
        String studyCentre = "UPC Medical";
        String phoneNumber = "555-0101";

        // Act
        Doctor doctor = new Doctor(firstName, lastName, email, specialization, cmpCode, studyCentre, phoneNumber);

        // Assert
        assertEquals(firstName, doctor.getFirstName());
        assertEquals(lastName, doctor.getLastName());
        assertEquals(email, doctor.getEmail());
        assertEquals(specialization, doctor.getSpecialization());
        assertEquals(cmpCode, doctor.getCmpCode());
        assertEquals(studyCentre, doctor.getStudyCentre());
        assertEquals(phoneNumber, doctor.getPhoneNumber());
    }
}
