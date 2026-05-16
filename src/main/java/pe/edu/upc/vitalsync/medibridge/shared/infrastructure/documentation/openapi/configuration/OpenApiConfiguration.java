package pe.edu.upc.vitalsync.medibridge.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI mediBridgeOpenApi() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .description("""
                                                        JWT Authentication for secured endpoints.
                                                        Insert the access token obtained during login.
                                                        """)
                                )
                )
                .info(
                        new Info()
                                .title("MediBridge API")
                                .description("""
                                        MediBridge is a management platform
                                        designed to improve communication, monitoring, and operational
                                        management between geriatric care centers, healthcare staff,
                                        residents, and their families.

                                        The platform provides secure RESTful APIs for:
                                        - Identity and Access Management
                                        - Health Monitoring
                                        - Medical Appointments
                                        - Messaging and Notifications
                                        - Resident Management

                                        Academic project developed by students from Universidad Peruana de Ciencias Aplicadas (UPC).
                                        """)
                                .version("v1.0.0")
                                .contact(
                                        new Contact()
                                                .name("VitalSync Team")
                                                .url("https://github.com/upc-pre-202601-si657-7944-VitalSync")
                                )
                                .license(
                                        new License()
                                                .name("Apache License 2.0")
                                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("MediBridge Web Service Repository")
                                .url("https://github.com/upc-pre-202601-si657-7944-VitalSync/medibridge.webservice")
                );
    }
}