package pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest.resources;

import java.util.Set;

public record AuthResponse(
        String token,
        Long userId,
        String firstName,
        String lastName,
        String email,
        Set<String> roles
) {}
