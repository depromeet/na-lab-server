package me.nalab.survey.application.port.in.web.feedbacksummary;

/**
 * 질문 폼에 해당하는 피드백의 요약 정보를 반환하는 인터페이스입니다.
 */
public interface FeedbackSummaryFindUseCase {

	/**
	 * surveyId에 해당하는 survey의 전체 피드백의 수를
	 * 반환합니다
	 */
	int getTotalFeedbackCount(Long surveyId);

	/**
	 * surveyId에 해당하는 survey의 읽지 않은 피드백의 수를
	 * 반환합니다
	 */
	int getUpdatedFeedbackCount(Long surveyId);
}
