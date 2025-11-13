package com.campusconnect.repository;

import com.campusconnect.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find notifications by user
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Find unread notifications by user
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.status = 'UNREAD' ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(@Param("userId") Long userId);

    // Count unread notifications
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.status = 'UNREAD'")
    long countUnreadByUserId(@Param("userId") Long userId);

    // Find notifications by type
    List<Notification> findByUserIdAndTypeOrderByCreatedAtDesc(Long userId, Notification.NotificationType type);

    // Find recent notifications (last 7 days)
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.createdAt >= :since ORDER BY n.createdAt DESC")
    List<Notification> findRecentByUserId(@Param("userId") Long userId, @Param("since") LocalDateTime since);

    // Find notifications by reference
    List<Notification> findByReferenceIdAndReferenceType(Long referenceId, String referenceType);

    // Delete old read notifications (cleanup)
    @Query("DELETE FROM Notification n WHERE n.status = 'READ' AND n.createdAt < :cutoffDate")
    void deleteOldReadNotifications(@Param("cutoffDate") LocalDateTime cutoffDate);
}