
    package com.campusconnect.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "lost_items")
    public class LostItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String itemName;
        private String description;
        private String location;
        private String status; // PENDING, FOUND, CLAIMED

        @CreatedDate
        private LocalDateTime createdAt;

        @LastModifiedDate
        private LocalDateTime updatedAt;

        @ManyToOne
        private User user;
    }
