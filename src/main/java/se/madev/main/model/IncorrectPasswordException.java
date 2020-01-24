package se.madev.main.model;

public class IncorrectPasswordException extends Exception {
	public IncorrectPasswordException() {
		super();
	}
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
