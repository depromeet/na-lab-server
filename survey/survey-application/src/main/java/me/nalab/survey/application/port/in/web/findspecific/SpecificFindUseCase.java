package me.nalab.survey.application.port.in.web.findspecific;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;

/**
 * 개별 Feedback의 구체 정보를 반환하는 UseCase 입니다.
 */
public interface SpecificFindUseCase {

	/**
	 * feedbackId를 입력으로 받아, feedbackId에 해당하는 FeedbackDto를 반환합니다.
	 *
	 * @param feedbackId Feedback의 id
	 * @return FeedbackDto, Feedback을 FeedbackDto로 변환한 Dto type 을 반환합니다.
	 */
	FeedbackDto findFeedbackByFeedbackId(Long feedbackId);

	/**
	 * feedbackId를 입력으로 받아, 해당 Feedback의 isRead를 true로 변경합니다.
	 *
	 * @param feedbackId Feedback의 id
	 */
	void updateFeedbackIsReadByFeedbackId(Long feedbackId);

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 SurveyDto를 반환합니다.
	 *
	 * @param surveyId survey의 id
	 * @return SurveyDto, Survey를 SurveyDto로 변환한 Dto type 을 반환합니다.
	 */
	SurveyDto findSurveyBySurveyId(Long surveyId);
}
