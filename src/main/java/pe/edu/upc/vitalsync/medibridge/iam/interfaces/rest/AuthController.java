package pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.vitalsync.medibridge.iam.application.internal.security.JwtService;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.Role;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.aggregates.User;
import pe.edu.upc.vitalsync.medibridge.iam.domain.model.entities.RoleName;
import pe.edu.upc.vitalsync.medibridge.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.upc.vitalsync.medibridge.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.upc.vitalsync.medibridge.iam.interfaces.rest.resources.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = users.findByEmail(request.email()).orElseThrow();
        var token = jwtService.generateToken(user);

        var roleNames = user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet());
        return ResponseEntity.ok(new AuthResponse(
                token, user.getId(),
                user.getFirstName(), user.getLastName(), user.getEmail(),
                roleNames
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResource> register(@Valid @RequestBody RegisterRequest req) {
        if (users.existsByEmail(req.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var u = new User(req.firstName(), req.lastName(), req.email(), passwordEncoder.encode(req.password()));

        Set<Role> roleSet = (req.roles() == null || req.roles().isEmpty())
                ? Set.of(roles.findByName(RoleName.PATIENT).orElseGet(() -> roles.save(new Role(RoleName.PATIENT))))
                : req.roles().stream().map(String::toUpperCase)
                .map(RoleName::valueOf)
                .map(rn -> roles.findByName(rn).orElseGet(() -> roles.save(new Role(rn))))
                .collect(Collectors.toSet());

        u.setRoles(roleSet);
        users.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResource.from(u));
    }
}
