package me.nalab.survey.application.port.out.persistence.feedbacksummary;

/**
 * surveyId에 해당하는 전체 Feedback의 수를 알려주는 역할을 하는 인터페이스 입니다.
 */
public interface TotalFeedbackCountPort {

	/**
	 * surveyId에 해당하는 전체 Feedback의 수를 반환합니다.
	 *
	 * @param surveyId Survey의 ID
	 * @return int 만약, 어떠한 피드백도 없을 경우, 0을 반환해야 합니다.
	 */
	int getTotalFeedbackCountBySurveyId(Long surveyId);
}
