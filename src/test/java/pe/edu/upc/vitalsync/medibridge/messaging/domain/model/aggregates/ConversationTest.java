package pe.edu.upc.vitalsync.medibridge.messaging.domain.model.aggregates;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ConversationTest {

    @Test
    @DisplayName("Conversation constructor should set all fields (AAA)")
    void constructor_ShouldSetAllFields() {
        // Arrange
        Long doctorId = 7L;
        Long patientId = 9L;
        String doctorName = "Dra. Ana";
        String patientName = "Luis";

        // Act
        Conversation conversation = new Conversation(doctorId, patientId, doctorName, patientName);

        // Assert
        assertEquals(doctorId, conversation.getDoctorId());
        assertEquals(patientId, conversation.getPatientId());
        assertEquals(doctorName, conversation.getDoctorName());
        assertEquals(patientName, conversation.getPatientName());
        assertNull(conversation.getLastMessagePreview());
        assertNull(conversation.getLastMessageAt());
        assertEquals(0, conversation.getUnreadForDoctor());
        assertEquals(0, conversation.getUnreadForPatient());
    }

    @Test
    @DisplayName("touch should update preview, time, and unread for patient (AAA)")
    void touch_ShouldUpdatePreviewAndUnreadForPatient() {
        // Arrange
        Conversation conversation = new Conversation(7L, 9L, "Dra. Ana", "Luis");
        String preview = "Hola";
        Date when = new Date();

        // Act
        conversation.touch(preview, when, true);

        // Assert
        assertEquals(preview, conversation.getLastMessagePreview());
        assertEquals(when, conversation.getLastMessageAt());
        assertEquals(0, conversation.getUnreadForDoctor());
        assertEquals(1, conversation.getUnreadForPatient());
    }

    @Test
    @DisplayName("touch should update preview, time, and unread for doctor (AAA)")
    void touch_ShouldUpdatePreviewAndUnreadForDoctor() {
        // Arrange
        Conversation conversation = new Conversation(7L, 9L, "Dra. Ana", "Luis");
        String preview = "Ok";
        Date when = new Date();

        // Act
        conversation.touch(preview, when, false);

        // Assert
        assertEquals(preview, conversation.getLastMessagePreview());
        assertEquals(when, conversation.getLastMessageAt());
        assertEquals(1, conversation.getUnreadForDoctor());
        assertEquals(0, conversation.getUnreadForPatient());
    }

    @Test
    @DisplayName("markRead methods should reset unread counters (AAA)")
    void markRead_ShouldResetUnreadCounts() {
        // Arrange
        Conversation conversation = new Conversation(7L, 9L, "Dra. Ana", "Luis");
        conversation.touch("Hola", new Date(), true);
        conversation.touch("Ok", new Date(), false);

        // Act
        conversation.markReadByDoctor();
        conversation.markReadByPatient();

        // Assert
        assertEquals(0, conversation.getUnreadForDoctor());
        assertEquals(0, conversation.getUnreadForPatient());
    }
}
