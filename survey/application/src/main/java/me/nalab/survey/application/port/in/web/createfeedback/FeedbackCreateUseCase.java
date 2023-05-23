package me.nalab.survey.application.port.in.web.createfeedback;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;

public interface FeedbackCreateUseCase {

	void createFeedback(Long surveyId, FeedbackDto feedbackDto);

}
