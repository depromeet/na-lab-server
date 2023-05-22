package me.nalab.survey.domain.exception;

import me.nalab.survey.domain.survey.spi.FeedbackValidable;

public final class IllegalFeedbackException extends RuntimeException {

	public IllegalFeedbackException(FeedbackValidable feedbackable) {
		super("Illegal feedback \"" + feedbackable + "\"");
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
