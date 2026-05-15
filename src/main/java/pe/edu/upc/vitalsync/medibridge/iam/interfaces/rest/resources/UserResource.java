package pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest.resources;

import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<String> roles
) {
    public static UserResource from(User u) {
        return new UserResource(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet())
        );
    }
}
