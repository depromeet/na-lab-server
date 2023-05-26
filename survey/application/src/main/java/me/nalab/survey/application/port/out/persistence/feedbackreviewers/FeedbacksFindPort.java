package me.nalab.survey.application.port.out.persistence.feedbackreviewers;

import java.util.List;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * SurveyId에 해당하는 모든 Feedback을 찾는 port 입니다.
 */
public interface FeedbacksFindPort {

	/**
	 * surveyId를 인자로 받아, 모든 Feedback 을 조회합니다.
	 * 만약, 어떠한 Feedback 도 찾을 수 없다면, 빈 리스트를 반환합니다.
	 *
	 * @param surveyId Feedback 을 조회할 survey 의 id
	 * @return List Survey에 응답한 모든 Feedback
	 */
	List<Feedback> findAllFeedback(Long surveyId);
}
