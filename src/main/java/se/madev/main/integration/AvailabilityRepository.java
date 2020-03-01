package se.madev.main.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Availability;

import java.util.ArrayList;

/**
 * Handles calls to the database regarding availabilities
 * @author madev
 *
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
	
	/**
	 * Fetches all availabilities coherent with the given application, referenced by its ID.
	 */
    ArrayList<Availability> findByApplicationId(int applicationID);
}
