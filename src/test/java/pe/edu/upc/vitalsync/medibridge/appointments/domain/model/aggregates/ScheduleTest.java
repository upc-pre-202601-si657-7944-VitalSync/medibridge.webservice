package pe.edu.upc.vitalsync.medibridge.appointments.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ScheduleTest {

    @Test
    @DisplayName("Schedule constructor should set all fields (AAA)")
    void constructor_ShouldSetAllFields() {
        // Arrange
        Long doctorId = 7L;
        String dayOfWeek = "Monday";
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(13, 0);
        Boolean isAvailable = true;

        // Act
        Schedule schedule = new Schedule(doctorId, dayOfWeek, startTime, endTime, isAvailable);

        // Assert
        assertEquals(doctorId, schedule.getDoctorId());
        assertEquals(dayOfWeek, schedule.getDayOfWeek());
        assertEquals(startTime, schedule.getStartTime());
        assertEquals(endTime, schedule.getEndTime());
        assertEquals(isAvailable, schedule.getIsAvailable());
    }

    @Test
    @DisplayName("updateAvailability should change availability (AAA)")
    void updateAvailability_ShouldUpdateAvailability() {
        // Arrange
        Schedule schedule = new Schedule(
                7L,
                "Monday",
                LocalTime.of(9, 0),
                LocalTime.of(13, 0),
                true
        );

        // Act
        schedule.updateAvailability(false);

        // Assert
        assertEquals(false, schedule.getIsAvailable());
    }

    @Test
    @DisplayName("updateTimeRange should change start and end time (AAA)")
    void updateTimeRange_ShouldUpdateTimes() {
        // Arrange
        Schedule schedule = new Schedule(
                7L,
                "Monday",
                LocalTime.of(9, 0),
                LocalTime.of(13, 0),
                true
        );

        LocalTime newStart = LocalTime.of(10, 0);
        LocalTime newEnd = LocalTime.of(14, 0);

        // Act
        schedule.updateTimeRange(newStart, newEnd);

        // Assert
        assertEquals(newStart, schedule.getStartTime());
        assertEquals(newEnd, schedule.getEndTime());
    }
}
