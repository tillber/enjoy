package se.madev.main.model;

public class UserDoesNotExistException extends Exception {
	public UserDoesNotExistException() {
		super();
	}
	public UserDoesNotExistException(String message) {
		super(message);
	}
}
