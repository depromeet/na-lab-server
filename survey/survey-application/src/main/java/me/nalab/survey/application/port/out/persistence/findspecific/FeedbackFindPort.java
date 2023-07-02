package me.nalab.survey.application.port.out.persistence.findspecific;

import java.util.Optional;

import me.nalab.survey.domain.feedback.Feedback;

/**
 * feedbackId를 받아 Feedback을 찾는 역할을 하는 인터페이스 입니다.
 */
public interface FeedbackFindPort {

	/**
	 * feedbackId에 해당하는 Feedback을 조회합니다.
	 *
	 * @param feedbackId feedback을 조회할 feedback의 ID
	 * @return Optional 만약, 어떠한 feedbackId도 없을 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Feedback> findFeedback(Long feedbackId);
}
