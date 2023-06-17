package me.nalab.survey.application.port.out.persistence.feedbackreviewers;

/**
 * Survey가 존재하는지 확인하는 인터페이스 입니다.
 */
public interface SurveyExistCheckPort {

	/**
	 * surveyId 를 인자로 받아, survey가 저장되어 있다면 true, 아니라면 false를 반환하는 인터페이스 입니다.
	 *
	 * @param surveyId 조회할 survey의 id 입니다.
	 * @return boolean survey가 있다면 true, 없다면 false를 반환해야 합니다.
	 */
	boolean isExistSurveyBySurveyId(Long surveyId);
}
