package me.nalab.user.application.exception;

public final class InvalidTokenException extends RuntimeException{

	public InvalidTokenException(String message) {
		super(message);
	}
}
