package me.nalab.survey.domain.exception;

public final class IdAlreadyGeneratedException extends RuntimeException {

	public <T> IdAlreadyGeneratedException(T t) {
		super("Id already generated \"" + t + "\"");
	}

}
