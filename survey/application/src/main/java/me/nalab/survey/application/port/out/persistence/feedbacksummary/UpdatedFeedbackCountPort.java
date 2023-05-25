package me.nalab.survey.application.port.out.persistence.feedbacksummary;

/**
 * surveyId에 해당하는 읽지 않은(최신의) Feedback의 수를 알려주는 역할을 하는 인터페이스 입니다.
 */
public interface UpdatedFeedbackCountPort {

	/**
	 * surveyId에 해당하는 읽지 않은 Feedback의 수를 반환합니다.
	 *
	 * @param surveyId Survey의 ID
	 * @return long 만약, 읽지 않은 피드백도 없을 경우, 0을 반환해야 합니다.
	 */
	long getUpdatedFeedbackCountBySurveyId(Long surveyId);
}
