package me.nalab.survey.domain.survey.spi;

import java.util.List;

import me.nalab.survey.domain.survey.Survey;

public interface FeedbackValidable<T extends QuestionFeedbackValidable> {

	boolean isValidFeedback(Survey survey);

	List<T> getAllQuestionFeedbackValidable();

}
