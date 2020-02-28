package se.madev.main.integration;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import se.madev.main.model.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	ArrayList<Competence> findAll();
}
