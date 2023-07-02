package me.nalab.survey.application.exception;

import lombok.Getter;

public final class TargetDoesNotExistException extends RuntimeException {

	@Getter
	private final Long id;

	public TargetDoesNotExistException(Long id) {
		super("Cannot find target id \"" + id + "\"");
		this.id = id;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
	
}
