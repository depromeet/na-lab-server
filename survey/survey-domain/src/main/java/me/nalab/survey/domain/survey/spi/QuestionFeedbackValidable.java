package me.nalab.survey.domain.survey.spi;

import me.nalab.survey.domain.survey.FormQuestionable;

public interface QuestionFeedbackValidable {

	Long getFormQuestionId();

	boolean isValidQuestionFeedback(FormQuestionable formQuestionable);

}
