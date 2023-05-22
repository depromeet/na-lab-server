package me.nalab.survey.application.port.in.web.survey.find;

import me.nalab.survey.application.common.survey.dto.SurveyDto;

/**
 * Survey를 조회하는 Usecase
 * 이 인터페이스를 이용해서 Survey를 조회할 수 있음
 */
public interface SurveyFindUseCase {

	/**
	 * surveyId에 해당하는 survey를
	 * SurveyDto형식으로 변환한 후 가져옴
	 */
	SurveyDto findSurvey(Long surveyId);

}
