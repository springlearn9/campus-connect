// UserRepository.java
package com.campusconnect.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.campusconnect.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

