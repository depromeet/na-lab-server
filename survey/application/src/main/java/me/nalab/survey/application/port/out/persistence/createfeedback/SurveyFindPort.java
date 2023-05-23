package me.nalab.survey.application.port.out.persistence.createfeedback;

import java.util.Optional;

import me.nalab.survey.domain.survey.Survey;

/**
 * Survey를 조회하는 인터페이스 입니다.
 */
public interface SurveyFindPort {

	/**
	 * survey의 id를 파라미터로 받아, Survey를 반환합니다.
	 *
	 * @param surveyId 조회할 survey의 id 입니다.
	 * @return Optional 조회된 Survey domain 입니다. 없다면 Optional.empty()를 반환해야합니다.
	 */
	Optional<Survey> findSurveyBySurveyId(Long surveyId);

}
