// LostItemRepository.java
package com.campusconnect.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.campusconnect.model.LostItem;

import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    
    // Override findAll to eagerly fetch user to avoid N+1 problem
    @Override
    @EntityGraph(attributePaths = {"user"})
    List<LostItem> findAll();
    
    // Custom query to find all with user data fetched in single query
    @Query("SELECT li FROM LostItem li JOIN FETCH li.user")
    List<LostItem> findAllWithUser();
    
}
