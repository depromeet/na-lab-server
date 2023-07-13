package me.nalab.survey.application.port.out.persistence.survey.find;

import java.util.Optional;

import me.nalab.survey.domain.survey.Survey;

/**
 * surveyId를 받아 Survey를 찾는 역할을 하는 인터페이스 입니다.
 */
public interface SurveyFindPort {

	/**
	 * surveyId에 해당하는 Survey를 조회합니다.
	 *
	 * @param surveyId survey를 조회할 Survey의 ID
	 * @return Optional 만약, 어떠한 surveyId도 없을 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Survey> findSurvey(Long surveyId);
}
