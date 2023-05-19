package me.nalab.survey.jpa.adaptor.exception.target;

public class TargetDoesNotExistException extends RuntimeException {

	public TargetDoesNotExistException(Long targetId) {
		super("Cannot find Target where targetId \"" + targetId + "\"");
	}
}
