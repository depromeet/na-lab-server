package me.nalab.survey.application.port.in.web.findtarget;

import me.nalab.survey.application.common.survey.dto.TargetDto;

/**
 * SurveyID를 이용해 Target을 조회하는 Usecase
 * 이 인터페이스를 이용해서 Target을 조회할 수 있음
 */
public interface TargetFindBySurveyIdUseCase {

	/**
	 *
	 * @param surveyId 질문폼의 id
	 * @return 질문폼에 해당하는 타겟을 TargetDto 형식으로 반환해서 가져옴
	 */
	TargetDto findTargetBySurveyId(Long surveyId);

}
