// FoundItemRepository.java
package com.campusconnect.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.campusconnect.model.FoundItem;

import java.util.List;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    
    // Override findAll to eagerly fetch reportedBy and claimedBy to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"reportedBy", "claimedBy"})
    List<FoundItem> findAll();
    
    // Custom query to find all with user data fetched in single query
    @Query("SELECT fi FROM FoundItem fi JOIN FETCH fi.reportedBy LEFT JOIN FETCH fi.claimedBy")
    List<FoundItem> findAllWithUsers();
    
}

