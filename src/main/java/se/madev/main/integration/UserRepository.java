package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
    User findByUsername(String username);
=======
    User findByUsername(String userName);

    User findByEmail(String email);
>>>>>>> developmentA
}
