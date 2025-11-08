package com.campusconnect.repository;

import com.campusconnect.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // Find notices by status
    List<Notice> findByStatusOrderByCreatedAtDesc(String status);

    // Find notices by category
    List<Notice> findByCategoryOrderByCreatedAtDesc(String category);

    // Find notices by priority
    List<Notice> findByPriorityOrderByCreatedAtDesc(String priority);

    // Find active notices (not expired)
    @Query("SELECT n FROM Notice n WHERE n.status = 'ACTIVE' AND (n.validUntil IS NULL OR n.validUntil > :currentDate) ORDER BY n.createdAt DESC")
    List<Notice> findActiveNotices(@Param("currentDate") LocalDateTime currentDate);

    // Find notices by title containing keyword
    List<Notice> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String keyword);

    // Find notices by posted user
    List<Notice> findByPostedByIdOrderByCreatedAtDesc(Long userId);

    // Find high priority active notices
    @Query("SELECT n FROM Notice n WHERE n.priority = 'HIGH' AND n.status = 'ACTIVE' AND (n.validUntil IS NULL OR n.validUntil > :currentDate) ORDER BY n.createdAt DESC")
    List<Notice> findHighPriorityActiveNotices(@Param("currentDate") LocalDateTime currentDate);
}