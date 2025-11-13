package com.campusconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status = NotificationStatus.UNREAD;

    @Column(name = "reference_id")
    private Long referenceId; // ID of the related entity (event, notice, lost item, etc.)

    @Column(name = "reference_type")
    private String referenceType; // EVENT, NOTICE, LOST_ITEM, FOUND_ITEM, etc.

    @Column(name = "action_url")
    private String actionUrl; // URL to navigate when notification is clicked

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User sender;

    // Notification types
    public enum NotificationType {
        NEW_EVENT,          // New event posted
        EVENT_UPDATE,       // Event details updated
        NEW_NOTICE,         // New notice posted
        NOTICE_UPDATE,      // Notice updated
        ITEM_FOUND,         // Someone found your lost item
        ITEM_CLAIMED,       // Someone claimed your found item
        NEW_MESSAGE,        // New message in chat
        SYSTEM_ALERT,       // System-wide alerts
        REMINDER           // Reminders for events, deadlines, etc.
    }

    // Notification status
    public enum NotificationStatus {
        UNREAD,
        READ,
        ARCHIVED
    }
}