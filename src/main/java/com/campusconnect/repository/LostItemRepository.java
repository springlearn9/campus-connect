// LostItemRepository.java
package com.campusconnect.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.campusconnect.model.LostItem;
public interface LostItemRepository extends JpaRepository<LostItem, Long> {}
