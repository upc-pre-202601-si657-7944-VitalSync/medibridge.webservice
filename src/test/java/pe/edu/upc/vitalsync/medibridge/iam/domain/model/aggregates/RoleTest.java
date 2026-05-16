package pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.entities.RoleName;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoleTest {

    @Test
    @DisplayName("Role constructor should set name (AAA)")
    void constructor_ShouldSetName() {
        // Arrange
        RoleName name = RoleName.DOCTOR;

        // Act
        Role role = new Role(name);

        // Assert
        assertEquals(name, role.getName());
    }
}

