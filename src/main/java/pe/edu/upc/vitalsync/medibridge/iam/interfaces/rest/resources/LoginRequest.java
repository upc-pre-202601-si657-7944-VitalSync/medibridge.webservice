package pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email @NotBlank String email,
        @NotBlank String password
) {}
