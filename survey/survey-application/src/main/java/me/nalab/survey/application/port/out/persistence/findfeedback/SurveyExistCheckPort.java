package me.nalab.survey.application.port.out.persistence.findfeedback;

/**
 * 저장된 Survey가 존재하는지 확인하는 port 입니다.
 */
public interface SurveyExistCheckPort {

	/**
	 * surveyId를 인자로 받아, 저장되어있는 Survey가 있는지 확인합니다.
	 * 있다면, true를 반환하고, 없다면 false를 반환합니다.
	 *
	 * @param surveyId 저장되어있는지 확인할 survey의 Id
	 * @return boolean 있다면 true / 없다면 false
	 */
	boolean isExistSurveyBySurveyId(Long surveyId);

}
