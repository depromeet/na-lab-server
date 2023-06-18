package me.nalab.survey.application.port.out.persistence.findfeedback;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * Feedback을 업데이트하는 인터페이스 입니다.
 */
public interface FeedbackUpdatePort {

	/**
	 * Feedback domain을 인자로 받아, 업데이트합니다.
	 *
	 * @param feedback 생성할 feedback domain
	 */
	void updateFeedback(Feedback feedback);
}
