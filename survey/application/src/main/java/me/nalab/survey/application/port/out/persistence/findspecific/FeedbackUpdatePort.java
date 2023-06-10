package me.nalab.survey.application.port.out.persistence.findspecific;

/**
 * 개별 피드백 조회 시 해당 피드백의 isRead 를 true로 변경하는 인터페이스 입니다.
 */
public interface FeedbackUpdatePort {

	/**
	 * feedbackID에 해당하는 Feedback의 isRead를 true로 변환합니다.
	 *
	 * @param feedbackId Feedback의 ID
	 */
	void updateFeedbackIsReadByFeedbackId(Long feedbackId);
}
