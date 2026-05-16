package pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record RegisterRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email,
        @NotBlank String password,
        Set<String> roles // e.g. ["DOCTOR"] / ["PATIENT"] / ["ADMIN"]
) {}
