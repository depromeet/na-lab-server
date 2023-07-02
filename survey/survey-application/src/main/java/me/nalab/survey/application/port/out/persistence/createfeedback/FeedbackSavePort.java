package me.nalab.survey.application.port.out.persistence.createfeedback;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * Survey에 Feedback을 저장하는 인터페이스 입니다.
 */
public interface FeedbackSavePort {

	/**
	 * Feedback domain을 인자로 받아, 저장합니다.
	 *
	 * @param feedback 생성할 feedback domain
	 */
	void saveFeedback(Feedback feedback);

}
