package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;

/**
 * Handles calls to the database regarding roles.
 * @author madev
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
	/**
	 * Fetches the Role with the given type.
	 */
	Role findByType(Type type);
}
