package se.madev.main.integration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.madev.main.model.Application;
import se.madev.main.model.Status;
import se.madev.main.model.User;

/**
 * Handles calls to the database regarding applications.
 * @author madev
 *
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	/**
	 * Returns the application made by the given user applicant.
	 */
    Application findByApplicant(User applicant);
    
    /**
     * Checks if the given applicant has sent an application, and if so returns it.
     */
    Application existsByApplicant(User applicant);
    
    /**
     * Returns all of the sent applications.
     */
    List<Application> findAll();
}
