package com.campusconnect.service;

import com.campusconnect.model.Notification;
import com.campusconnect.model.User;
import com.campusconnect.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Notification createNotification(User user, String title, String message, 
                                         Notification.NotificationType type, 
                                         Long referenceId, String referenceType) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setReferenceId(referenceId);
        notification.setReferenceType(referenceType);
        notification.setStatus(Notification.NotificationStatus.UNREAD);

        Notification savedNotification = notificationRepository.save(notification);

        // Send real-time notification via WebSocket
        sendRealTimeNotification(user.getId(), savedNotification);

        return savedNotification;
    }

    public void sendRealTimeNotification(Long userId, Notification notification) {
        try {
            messagingTemplate.convertAndSendToUser(
                userId.toString(), 
                "/queue/notifications", 
                notification
            );
        } catch (Exception e) {
            // Log error but don't fail the notification creation
            System.err.println("Failed to send real-time notification: " + e.getMessage());
        }
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }

    public long getUnreadCount(Long userId) {
        return notificationRepository.countUnreadByUserId(userId);
    }

    public Notification markAsRead(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setStatus(Notification.NotificationStatus.READ);
            notification.setReadAt(LocalDateTime.now());
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notification not found");
    }

    public void markAllAsRead(Long userId) {
        List<Notification> unreadNotifications = notificationRepository.findUnreadByUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        
        for (Notification notification : unreadNotifications) {
            notification.setStatus(Notification.NotificationStatus.READ);
            notification.setReadAt(now);
        }
        
        notificationRepository.saveAll(unreadNotifications);
    }

    public void deleteNotification(Long notificationId, Long userId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent() && notification.get().getUser().getId().equals(userId)) {
            notificationRepository.deleteById(notificationId);
        } else {
            throw new RuntimeException("Notification not found or unauthorized");
        }
    }

    // Broadcast notifications to all users (for system-wide announcements)
    public void broadcastNotification(String title, String message, Notification.NotificationType type) {
        // This would typically get all active users and create notifications for them
        // For now, we'll just send a general broadcast
        messagingTemplate.convertAndSend("/topic/announcements", 
            new Notification(null, title, message, type, Notification.NotificationStatus.UNREAD, 
                           null, null, null, LocalDateTime.now(), null, null, null));
    }

    // Cleanup old notifications (can be scheduled)
    public void cleanupOldNotifications() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30); // Keep notifications for 30 days
        notificationRepository.deleteOldReadNotifications(cutoffDate);
    }
}