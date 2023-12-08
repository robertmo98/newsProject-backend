package edu.robertmo.newsproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Scientific news Project",
                description = "Scientific news project - backend (server).  ",
                version = "1.0",
                contact = @Contact(
                        name = "Robert Mopsik",
                        email = "robertmof9@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "http://localhost:3000/terms"
        )
)

@SecuritySchemes(
        @SecurityScheme(
                name = "Bearer Authentication",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "Bearer"
        )
)
public class OpenApiConfig {
}
