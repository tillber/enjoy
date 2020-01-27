package se.madev.main.controller;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import se.madev.main.integration.DBHandler;
import se.madev.main.model.IncorrectPasswordException;
import se.madev.main.model.User;
import se.madev.main.model.UserDoesNotExistException;

public class ApplicationService {
	DBHandler repository;
	
	public ApplicationService() {
		repository = new DBHandler();
	}
	
	public Authentication authenticate(Authentication authentication) throws IncorrectPasswordException, UserDoesNotExistException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		if(new User(username, password).equals(new User("hello", "world"))) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		}else {
			throw new IncorrectPasswordException();
		}
		/*try {
			
	    } catch(UserDoesNotExistException exception) {
			throw exception;
		}*/
	}
}