package me.nalab.survey.application.port.in.web.findfeedback;

import java.util.List;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;

/**
 * Feedback을 찾는 UseCase 입니다.
 */
public interface FeedbackFindUseCase {

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 모든 FeedbackDto를 반환합니다.
	 *
	 * @param surveyId Feedback이 저장된 survey의 id
	 * @return List survey에 저장된 모든 feedback 만약, 어떠한 feedback도 없다면, 빈 List를 반환합니다.
	 */
	List<FeedbackDto> findAllFeedbackDtoBySurveyId(Long surveyId);

	/**
	 * surveyId에 해당하는 모든 Feedback의 FormFeedbackEntity들의 isRead를 true로 변경합니다.
	 *
	 * @param surveyId Survey의 id
	 */
	void updateFormFeedbackEntityIsReadBySurveyId(Long surveyId);
}
