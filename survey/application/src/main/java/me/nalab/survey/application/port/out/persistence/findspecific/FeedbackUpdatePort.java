package me.nalab.survey.application.port.out.persistence.findspecific;


import me.nalab.survey.domain.feedback.Feedback;

/**
 * 개별 피드백 조회 시 해당 피드백의 isRead 를 true로 변경하는 인터페이스 입니다.
 */
public interface FeedbackUpdatePort {


	/**
	 * Feedback domain을 인자로 받아, 업데이트합니다.
	 *
	 * @param feedback 생성할 feedback domain
	 */
	void updateFeedback(Feedback feedback);
}
