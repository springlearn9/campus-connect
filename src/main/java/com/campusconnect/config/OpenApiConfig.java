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
                .title("Campus Connect API - Enhanced Edition")
                .version("2.0.0")
                .contact(contact)
                .description("""
                    Campus Connect is a comprehensive platform for managing campus life with advanced features:
                    
                    🎯 **Core Features:**
                    • Lost & Found Management with Image Support
                    • Event Management with Advanced Filtering
                    • Notice Board with Category-based Organization
                    • User Profile Management with Enhanced Fields
                    
                    🚀 **Advanced Features:**
                    • Real-time Notifications via WebSocket
                    • File Upload System for Images
                    • Advanced Search & Filtering
                    • Email Integration for Contact Management
                    • Multi-criteria Sorting and Pagination
                    
                    📱 **API Capabilities:**
                    • RESTful API Design with OpenAPI 3.0
                    • Comprehensive Error Handling
                    • File Upload Support (Images: JPG, PNG, GIF)
                    • WebSocket Support for Real-time Updates
                    • Email Notifications for Lost & Found Items
                    
                    👥 **User Roles:** STUDENT, FACULTY, ADMIN, STAFF
                    📊 **Item Status Tracking:** PENDING, SEARCHING, FOUND, CLAIMED, CLOSED
                    🔔 **Notification Types:** Events, Notices, Lost Items, System Alerts
                    
                    For WebSocket connection: ws://localhost:8080/ws
                    """)
                .termsOfService("https://github.com/springlearn9/campus-connect/blob/main/TERMS.md")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}