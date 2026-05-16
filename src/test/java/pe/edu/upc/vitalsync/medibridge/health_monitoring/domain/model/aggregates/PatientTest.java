package pe.edu.upc.vitalsync.medibridge.health_monitoring.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PatientTest {

    @Test
    @DisplayName("Patient constructor should set all fields (AAA)")
    void constructor_ShouldSetAllFields() {
        // Arrange
        String firstName = "Luis";
        String lastName = "Perez";
        String email = "luis.perez@example.com";
        Integer age = 30;
        String sex = "Male";
        String bloodType = "O+";
        String patientId = "PID-789";
        String healthInsurance = "SeguroPlus";
        String preferredHospital = "Hospital Central";
        String status = "Healthy";
        Long assignedDoctorId = 10L;
        LocalDate registerDate = LocalDate.of(2024, 1, 15);

        // Act
        Patient patient = new Patient(
                firstName,
                lastName,
                email,
                age,
                sex,
                bloodType,
                patientId,
                healthInsurance,
                preferredHospital,
                status,
                assignedDoctorId,
                registerDate
        );

        // Assert
        assertEquals(firstName, patient.getFirstName());
        assertEquals(lastName, patient.getLastName());
        assertEquals(email, patient.getEmail());
        assertEquals(age, patient.getAge());
        assertEquals(sex, patient.getSex());
        assertEquals(bloodType, patient.getBloodType());
        assertEquals(patientId, patient.getPatientId());
        assertEquals(healthInsurance, patient.getHealthInsurance());
        assertEquals(preferredHospital, patient.getPreferredHospital());
        assertEquals(status, patient.getStatus());
        assertEquals(assignedDoctorId, patient.getAssignedDoctorId());
        assertEquals(registerDate, patient.getRegisterDate());
    }
}
