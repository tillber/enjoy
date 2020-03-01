package se.madev.main.integration;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Competence;

/**
 * Handles calls to the database regarding competences.
 * @author madev
 *
 */
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	/**
	 * Fetches all of the competences in the database.
	 */
	ArrayList<Competence> findAll();
}
