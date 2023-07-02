package me.nalab.survey.application.port.in.web.createfeedback;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;

/**
 * 새로운 피드백을 생성하는 인터페이스 입니다.
 */
public interface FeedbackCreateUseCase {

	/**
	 * surveyId와 feedbackDto를 인자로 받고, surveyId에 해당하는 survey에 feedback을 남깁니다.
	 *
	 * @param surveyId feedback을 남길 survey의 id
	 * @param feedbackDto feedback의 정보들
	 */
	void createFeedback(Long surveyId, FeedbackDto feedbackDto);

}
