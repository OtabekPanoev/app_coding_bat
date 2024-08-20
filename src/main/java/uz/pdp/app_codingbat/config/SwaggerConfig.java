package uz.pdp.app_codingbat.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

@SecuritySchemes(value = {
        @SecurityScheme(
                name = "bearerAuth",
                description = "JWT token",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer",
                in = SecuritySchemeIn.HEADER
        ),
})
@OpenAPIDefinition(
        info = @Info(title = "Project",
                description = "For CodingBat app",
                contact = @Contact(
                        url = "https://project.uz",
                        email = "admin@project.uz"
                )),
        servers = {
                @Server(url = "http://localhost:8877", description = "Localhost"),
        },
        security = {
                @SecurityRequirement(name = "bearerAuth"),
        }
)
@Component
public class SwaggerConfig {
}
