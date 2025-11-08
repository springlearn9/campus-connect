package com.campusconnect.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI campusConnectOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development Server");

        Server prodServer = new Server();
        prodServer.setUrl("https://your-production-domain.com");
        prodServer.setDescription("Production Server");

        Contact contact = new Contact();
        contact.setEmail("springlearn9@example.com");
        contact.setName("Campus Connect Team");
        contact.setUrl("https://github.com/springlearn9/campus-connect");

        License mitLicense = new License().name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Campus Connect API")
                .version("1.0.0")
                .contact(contact)
                .description("Campus Connect is a comprehensive platform for managing lost and found items, campus events, and important notices. This API provides endpoints for students, faculty, and administrators to interact with the system.")
                .termsOfService("https://github.com/springlearn9/campus-connect/blob/main/TERMS.md")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}