package me.nalab.survey.domain.exception;

import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.survey.spi.FeedbackValidable;
import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;

public final class IllegalFeedbackException extends RuntimeException {

	public <T extends QuestionFeedbackValidable> IllegalFeedbackException(Survey survey,
		FeedbackValidable<T> feedbackable) {
		super("Illegal feedback exception Survey \"" + survey + "\" FeedbacValidable \"" + feedbackable + "\"");
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
