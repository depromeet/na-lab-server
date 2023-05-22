package me.nalab.survey.domain.exception;

import me.nalab.survey.domain.survey.spi.FeedbackValidable;
import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;

public final class IllegalFeedbackException extends RuntimeException {

	public <T extends QuestionFeedbackValidable> IllegalFeedbackException(FeedbackValidable<T> feedbackable) {
		super("Illegal feedback \"" + feedbackable + "\"");
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
