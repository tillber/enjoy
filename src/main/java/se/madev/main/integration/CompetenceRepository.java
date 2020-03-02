package se.madev.main.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import se.madev.main.model.Competence;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	ArrayList<Competence> findAll();

	Competence findByName(String name);
	Competence findById(int id);
}
