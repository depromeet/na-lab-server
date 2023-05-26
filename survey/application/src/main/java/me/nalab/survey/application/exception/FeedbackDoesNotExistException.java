package me.nalab.survey.application.exception;

import lombok.Getter;

public class FeedbackDoesNotExistException extends RuntimeException {

	@Getter
	private final Long feedbackId;

	public FeedbackDoesNotExistException(Long feedbackId) {
		super("Cannot find any feedbackId \"" + feedbackId);
		this.feedbackId = feedbackId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
