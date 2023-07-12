package me.nalab.survey.application.port.out.persistence.findfeedback.type;

import java.util.Optional;

import me.nalab.survey.domain.survey.Survey;

/**
 * Survey를 찾는 인터페이스입니다.
 */
public interface SurveyFindPort {

	/**
	 *
	 * @param surveyId surveyId 질문폼에 해당하는 id
	 * @return Optional 만약, survey가 존재하지 않으면 Optional.empty() 를 반환합니다.
	 */
	Optional<Survey> findSurveyById(Long surveyId);

}
