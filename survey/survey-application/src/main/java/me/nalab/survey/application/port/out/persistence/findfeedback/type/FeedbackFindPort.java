package me.nalab.survey.application.port.out.persistence.findfeedback.type;

import java.util.List;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * Feedback을 찾는 인터페이스입니다.
 */
public interface FeedbackFindPort {

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 모든 Feedback을 반환합니다.
	 *
	 * @param surveyId 질문폼에 해당하는 id
	 * @return 해당하는 모든 Feedback 만약, 어떠한 FeedbackDto도 없다면, 빈 List를 반환합니다.
	 */
	List<Feedback> findFeedbackBySurveyId(Long surveyId);

}
