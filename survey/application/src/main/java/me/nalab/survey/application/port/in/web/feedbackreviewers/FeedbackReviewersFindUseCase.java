package me.nalab.survey.application.port.in.web.feedbackreviewers;

import java.util.List;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * 질문 폼의 모든 피드백을 반환하는 인터페이스입니다.
 */
public interface FeedbackReviewersFindUseCase {

	/**
	 * surveyId를 인자로 받고, surveyId의 모든 피드백을 반환합니다.
	 *
	 * @param surveyId 조회할 survey의 id
	 * @return Feedback의 List를 반환합니다
	 * 없다면, 빈 리스트를 반환합니다.
	 */

	List<Feedback> findAllFeedback(Long surveyId);
}
