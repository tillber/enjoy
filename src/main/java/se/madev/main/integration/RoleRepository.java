package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import se.madev.main.model.Role;
import se.madev.main.model.Role.Type;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByType(Type type);

	Role findById(int id);
}
