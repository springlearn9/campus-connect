package com.campusconnect.repository;

import com.campusconnect.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by status
    List<Event> findByStatus(String status);

    // Find upcoming events
    @Query("SELECT e FROM Event e WHERE e.eventDate > :currentDate AND e.status = 'ACTIVE' ORDER BY e.eventDate ASC")
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

    // Find events by date range
    @Query("SELECT e FROM Event e WHERE e.eventDate BETWEEN :startDate AND :endDate ORDER BY e.eventDate ASC")
    List<Event> findEventsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Find events by title containing keyword
    List<Event> findByTitleContainingIgnoreCase(String keyword);

    // Find events by posted user
    List<Event> findByPostedByIdOrderByCreatedAtDesc(Long userId);

    // Find events by location
    List<Event> findByLocationContainingIgnoreCase(String location);
}