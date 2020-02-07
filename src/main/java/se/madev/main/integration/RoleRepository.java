package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByType(Type type);
}
