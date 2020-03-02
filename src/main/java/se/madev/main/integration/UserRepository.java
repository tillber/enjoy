package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import se.madev.main.model.User;

/**
 * Handles calls to the dataabase that involves the class and table User through JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(int id);

    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
