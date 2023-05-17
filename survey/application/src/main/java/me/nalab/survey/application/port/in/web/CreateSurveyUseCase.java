package me.nalab.survey.application.port.in.web;

import me.nalab.survey.application.common.dto.SurveyDto;

/**
 * 새로운 Survey를 생성하는 Usecase 이 인터페이스를 이용해서 새로운 Survey를 생성할 수 있음.
 */
public interface CreateSurveyUseCase {

	/**
	 * targetId에 해당하는 target에 새로운 Survey를 생성함.
	 * @param surveyDto SurveyDto 생성될 Survey의 정보들
	 */
	void createSurvey(Long targetId, SurveyDto surveyDto);

}
