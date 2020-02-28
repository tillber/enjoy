package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Status;
import se.madev.main.model.Status.Type;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	Status findByType(Type type);
}
