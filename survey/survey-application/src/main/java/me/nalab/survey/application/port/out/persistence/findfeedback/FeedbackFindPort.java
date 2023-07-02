package me.nalab.survey.application.port.out.persistence.findfeedback;

import java.util.List;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * 저장되어있는 모든 Feedback 을 조회하는 인터페이스 입니다.
 */
public interface FeedbackFindPort {

	/**
	 * surveyId를 인자로받아, 저장되어있는 모든 Feedback을 조회합니다.
	 * 만약, 어떠한 저장되어있는 Feedback을 찾을 수 없다면, 빈 List를 반환합니다.
	 *
	 * @param surveyId Feedback 을 조회할 survey의 id
	 * @return List 조회된 Feedback의 list
	 */
	List<Feedback> findAllFeedbackBySurveyId(Long surveyId);

}
