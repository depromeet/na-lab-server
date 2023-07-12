package me.nalab.survey.application.exception;

import lombok.Getter;

public class FormQuestionFeedbackNotExistException extends RuntimeException {

	@Getter
	private final Long formQuestionFeedbackId;

	public FormQuestionFeedbackNotExistException(Long formQuestionFeedbackId) {
		super("Cannot found any formQuestionFeedback id \"" + formQuestionFeedbackId + "\"");
		this.formQuestionFeedbackId = formQuestionFeedbackId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
