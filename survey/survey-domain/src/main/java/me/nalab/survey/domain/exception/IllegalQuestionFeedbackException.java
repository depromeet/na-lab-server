package me.nalab.survey.domain.exception;

import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;

public final class IllegalQuestionFeedbackException extends RuntimeException {

	public IllegalQuestionFeedbackException(QuestionFeedbackValidable questionFeedbackable) {
		super("Illegal question feedback \"" + questionFeedbackable + "\"");
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
