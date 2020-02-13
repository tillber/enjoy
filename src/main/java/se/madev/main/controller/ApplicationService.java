package se.madev.main.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;
import se.madev.main.integration.CompetenceRepository;
import se.madev.main.integration.DBHandler;
import se.madev.main.model.Competence;
import se.madev.main.model.User;

@Service
public class ApplicationService {


	@Autowired
	CompetenceRepository competenceRepository;

	//@Autowired
	//ApplicationRepository applicationRepository;
	
	public List<Competence> getCompetences(){
		List<Competence> competence = competenceRepository.findAll();

		return competence;
	}
}