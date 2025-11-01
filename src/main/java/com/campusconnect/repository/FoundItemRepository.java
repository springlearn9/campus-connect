// FoundItemRepository.java
package com.campusconnect.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.campusconnect.model.FoundItem;
public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {}

