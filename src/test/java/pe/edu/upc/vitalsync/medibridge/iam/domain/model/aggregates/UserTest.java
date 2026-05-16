package pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
    @DisplayName("User constructor should set all fields and initialize roles (AAA)")
    void constructor_ShouldSetAllFieldsAndInitRoles() {
        // Arrange
        String firstName = "Ana";
        String lastName = "Ruiz";
        String email = "ana.ruiz@example.com";
        String password = "secret";

        // Act
        User user = new User(firstName, lastName, email, password);

        // Assert
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getRoles());
        assertEquals(0, user.getRoles().size());
    }
}

