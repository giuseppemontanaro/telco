package it.polimi.model.exceptions;


public class UserNotFound extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFound(String message) {
		super(message);
	}
}