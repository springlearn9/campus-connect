
    package com.campusconnect.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "found_items")
    public class FoundItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String itemName;
        private String description;
        private String location;
        private String photoUrl;

        @CreatedDate
        private LocalDateTime foundDate;

        @ManyToOne
        private User reportedBy;
    }

