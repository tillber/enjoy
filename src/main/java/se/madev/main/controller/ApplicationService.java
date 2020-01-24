package se.madev.main.controller;

import se.madev.main.integration.DBHandler;
import se.madev.main.model.IncorrectPasswordException;
import se.madev.main.model.User;
import se.madev.main.model.UserDoesNotExistException;

public class ApplicationService {
	DBHandler repository;
	
	public ApplicationService() {
		repository = new DBHandler();
	}
	
	public String authenticate(String username, String password) throws IncorrectPasswordException, UserDoesNotExistException {
		User user = new User(username, password);
		if(user.equals(new User("hello", "world"))) {
			String token = Authorizer.createToken("1", "Controller", "login", (long)50);
			return token;
		}else {
			throw new IncorrectPasswordException();
		}
		/*try {
			
	    } catch(UserDoesNotExistException exception) {
			throw exception;
		}*/
	}
}