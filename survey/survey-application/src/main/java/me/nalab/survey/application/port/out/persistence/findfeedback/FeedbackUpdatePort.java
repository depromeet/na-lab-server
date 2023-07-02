package me.nalab.survey.application.port.out.persistence.findfeedback;

import java.util.List;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * Feedback을 업데이트하는 인터페이스 입니다.
 */
public interface FeedbackUpdatePort {

	/**
	 * FeedbackList를 domain 인자로 받아, 업데이트합니다.
	 *
	 * @param List<Feedback> 업데이트할 feedback List
	 */
	void updateFeedback(List<Feedback> feedbackList);
}

