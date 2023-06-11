package me.nalab.survey.application.port.out.persistence.findspecific;

import java.util.Optional;

/**
 * 개별 피드백 조회 시 해당 피드백의 isRead 를 true로 변경하는 인터페이스 입니다.
 */
public interface FeedbackUpdatePort {

	/**
	 * feedbackID에 해당하는 Feedback의 isRead를 true로 변환합니다.
	 *
	 * @param feedbackId Feedback의 ID
	 * @return Optional 만약, feedbackId가 없는 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Long> updateFeedbackIsReadByFeedbackId(Long feedbackId);
}
