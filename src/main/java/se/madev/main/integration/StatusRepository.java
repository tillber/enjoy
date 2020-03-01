package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Status;
import se.madev.main.model.Status.Type;

/**
 * Handles calls to the database regarding application statuses.
 * @author madev
 *
 */
public interface StatusRepository extends JpaRepository<Status, Integer> {
	/**
	 * Retrieves a Status with the given type.
	 */
	Status findByType(Type type);
}
